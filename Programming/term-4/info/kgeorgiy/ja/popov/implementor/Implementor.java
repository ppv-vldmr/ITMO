package info.kgeorgiy.ja.popov.implementor;

import info.kgeorgiy.java.advanced.implementor.Impler;
import info.kgeorgiy.java.advanced.implementor.ImplerException;
import info.kgeorgiy.java.advanced.implementor.JarImpler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.jar.JarOutputStream;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

/**
 * Class implementing {@link Impler}. Provides public methods to implement {@code .java}
 * files for classes implementing given interface.
 *
 * @author Popov Vladimir
 */
public class Implementor implements JarImpler {
    /**
     * Main function. Provides console interface for {@link Implementor}.
     * Runs in two modes depending on command {@code args}:
     * <ol>
     * <li>2-argument mode: {@code className outputPath} creates <code>.java</code> file by executing
     * provided with {@link Impler} method {@link #implement(Class, Path)}</li>
     * <li>3-argument mode: {@code -jar className jarOutputPath} creates <code>.jar</code> file by executing
     * provided with {@link JarImpler} method {@link #implementJar(Class, Path)}</li>
     * </ol>
     * All arguments must be correct and not-null. If some arguments are incorrect
     * or an error occurs in runtime an information message is printed and implementation is aborted.
     *
     * @param args command line arguments for application.
     */
    public static void main(final String[] args) {
        try {
            if (args == null || (args.length != 3 && args.length != 4)) {
                throw new IllegalArgumentException("Two or three arguments were expected");
            }
            for (final String arg : args) {
                if (arg == null) {
                    throw new IllegalArgumentException("Non-null arguments were expected");
                }
            }
            final Implementor implementor = new Implementor();

            if (args.length == 3) {
                implementor.implement(Class.forName(args[1]), Paths.get(args[2]));
            } else if (args[1].equals("-jar")) {
                implementor.implementJar(Class.forName(args[2]), Paths.get(args[3]));
            } else {
                throw new IllegalArgumentException(args[1] + " is unknown argument, -jar expected");
            }
        } catch (final IllegalArgumentException e) {
            System.err.println("Illegal arguments: " + e.getMessage());
        } catch (final ClassNotFoundException e) {
            System.err.println("Invalid class in the first argument: " + e.getMessage());
        } catch (final ImplerException e) {
            System.err.println("An error occurred during implementation: " + e.getMessage());
        }
    }

    /**
     * Creates a {@code .jar} file containing compiled sources of class
     * implemented by {@link #implement(Class, Path)} class in location specified by {@code jarFile}.
     *
     * @param token   type token to create implementation for
     * @param jarFile target {@code .jar} file
     * @throws ImplerException if any error occurs during the implementation
     */
    @Override
    public void implementJar(final Class<?> token, final Path jarFile) throws ImplerException {
        final Path tmpDir = Paths.get(".");
        implement(token, tmpDir);
        final String classFilename = Paths.get(getClassPackage(token).replace('.', File.separatorChar))
                .resolve(getClassSimpleName(token)).toString();

        compile(token, tmpDir, classFilename);

        try (final JarOutputStream writer = new JarOutputStream(Files.newOutputStream(jarFile))) {
            writer.putNextEntry(new ZipEntry(classFilename.replace(File.separatorChar, '/') + ".class"));
            Files.copy(Paths.get(tmpDir.resolve(classFilename) + ".class"), writer);
        } catch (final IOException e) {
            throw new ImplerException("Failed to write to jar file", e);
        }
    }

    private static void compile(final Class<?> token, final Path tmpDir, final String className) throws ImplerException {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        try {
            if (compiler.run(null, null, null, "-classpath",
                    tmpDir.getFileName() + File.pathSeparator +
                            Path.of(token.getProtectionDomain().getCodeSource().getLocation().toURI()).toString(),
                    tmpDir.resolve(className).toString() + ".java") != 0) {
                throw new ImplerException("Failed to compile files");
            }
        } catch (final URISyntaxException e) {
            throw new ImplerException("URISyntaxException", e);
        }
    }

    /**
     * Simple name of generated class.
     * @param token given {@link Class} to implement from
     * @return a {@link String} representing simple name of generated class
     */
    protected static String getClassSimpleName(final Class<?> token) {
        return token.getSimpleName() + "Impl";
    }

    /**
     * Package of generated class.
     * @param token given {@link Class} to implement from
     * @return a {@link String} representing package of generated class
     */
    protected static String getClassPackage(final Class<?> token) {
        return token.getPackage().getName();
    }

    /**
     * Unicode encoder for resulting {@code .java} file.
     * Escapes all unicode characters in {@code \\u} notation.
     *
     * @param arg a {@link String} instance to be encoded
     * @return a {@link String} representing unicode-escaped {@code arg}
     */
    private static String toUnicode(final String arg) {
        final StringBuilder builder = new StringBuilder();
        for (final char c : arg.toCharArray()) {
            builder.append(c < 128 ? String.valueOf(c) : String.format("\\u%04x", (int) c));
        }
        return builder.toString();
    }

    /**
     * Writes class head implementations of a given {@link Class}
     * through the specified {@code BufferedWriter}.
     *
     * @param token  given {@link Class} to implement
     * @param writer given {@link BufferedWriter}
     * @throws IOException If an I/ O error occurs during recording
     */
    private static void implementClassHead(final Class<?> token, final BufferedWriter writer) throws IOException {
        final StringBuilder res = new StringBuilder();

        // :NOTE: Упростить
        if (!getClassPackage(token).isEmpty()) {
            res.append("package ").append(getClassPackage(token)).append(";\n\n");
        }

        res.append(String.format("public class %s implements %s {%n", getClassSimpleName(token), token.getCanonicalName()));
        writer.write(toUnicode(res.toString()));
    }

    /**
     * Returns a representation of {@link Method} argument list with types and names.
     *
     * @param method an instance of {@link Method}
     * @return a {@link String} representing this argument list
     */
    private static String getArguments(final Method method) {
        return Arrays.stream(method.getParameters())
                .map(parameter -> (parameter.getType().getCanonicalName() + " ")
                        + parameter.getName()).collect(Collectors.joining(", ", "(", ")"));
    }

    /**
     * Returns default return value representation for method with given {@link Method#getReturnType()}.
     *
     * @param token some method return value type
     * @return a {@link String} representing default return value of this type
     */
    private static String getDefaultValue(final Class<?> token) {
        if (!token.isPrimitive()) {
            return " null";
        } else if (token.equals(void.class)) {
            return "";
        } else if (token.equals(boolean.class)) {
            return " false";
        } else {
            return " 0";
        }
    }

    /**
     * Writes {@link Method} implementation.
     * through the specified {@code BufferedWriter}.
     * Written implementation of given method returns default value of its return type.
     *
     * @param method     given {@link Method}
     * @param writer         given {@link BufferedWriter}
     * @param executableName given {@link String}
     * @throws IOException If an I/O error occurs during recording
     */
    private static void getMethodImplementation(final Method method, final BufferedWriter writer, final String executableName) throws IOException {
        String res = String.format("\tpublic %s %s%s {\n\t\treturn%s;\n\t}\n\n",
                method.getReturnType().getCanonicalName(), executableName, getArguments(method), getDefaultValue(method.getReturnType()));
        writer.write(toUnicode(res));
    }

    /**
     * Writes implementations of all methods of a given {@link Class}
     * through the specified {@code BufferedWriter}.
     *
     * @param token  given {@link Class} to implement methods from
     * @param writer given {@link BufferedWriter}
     * @throws IOException If an I/O error occurs during recording
     */
    private static void implementMethods(final Class<?> token, final BufferedWriter writer) throws IOException {
        for (final Method method : token.getMethods()) {
            if (Modifier.isPublic(method.getModifiers())) {
                getMethodImplementation(method, writer, method.getName());
            }
        }
    }

    /**
     * Builds a path from the specified parts {@link Path}, {@link String} and returns it.
     * @param path given {@link Path} representing path
     * @param classPackage class package
     * @param classSimpleName simple class name
     * @return a {@link File} representing path to output file
     * @throws ImplerException if failed to create path to output file
     */
    private static File getOutputFile(final Path path, final String classPackage, final String classSimpleName) throws ImplerException {
        final File classFile = new File(Paths.get(path.toString(),
                classPackage.split("\\.")).resolve(classSimpleName).toString() + ".java");

        if (!classFile.exists()) {
            final File outputParent = classFile.getParentFile();
            if (!outputParent.exists() && !outputParent.mkdirs()) {
                throw new ImplerException("Failed to create path to output file");
            }
        }

        return classFile;
    }

    /**
     * Creates a {@code .java} file containing source code of a class extending or implementing
     * class or interface specified by {@code token} in location specified by {@code root}.
     *
     * @param token type token to create implementation for
     * @param root root directory
     * @throws ImplerException if any error occurs during the implementation
     */
    @Override
    public void implement(final Class<?> token, final Path root) throws ImplerException {
        if (!token.isInterface()) {
            throw new ImplerException("Incorrect class token, expected interface");
        }

        if (Modifier.isPrivate(token.getModifiers())) {
            throw new ImplerException("Unable to implement class from private Interface");
        }

        final File classFile = getOutputFile(root, token.getPackage().getName(), getClassSimpleName(token));

        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(classFile))) {
            implementClassHead(token, writer);

            implementMethods(token, writer);

            writer.write(toUnicode("}\n"));
        } catch (final IOException e) {
            throw new ImplerException("Failed to write to output file", e);
        }
    }
}
