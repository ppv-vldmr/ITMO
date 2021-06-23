package info.kgeorgiy.ja.popov.student;

import info.kgeorgiy.java.advanced.student.GroupName;
import info.kgeorgiy.java.advanced.student.Student;
import info.kgeorgiy.java.advanced.student.StudentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentDB implements StudentQuery {

    private <T>Stream<T> getStudentProperty(List<Student> students, Function<Student, T> function) {
        return students
                .stream()
                .map(function);
    }

    private <T>List<T> sortStudentsByProperty(Collection<T> students, Comparator<? super T> comparator) {
        return students
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private Stream<Student> findStudentsByProperty(Collection<Student> students, Function<Student, String> function, String check) {
        return students
                .stream()
                .filter(student -> function.apply(student).equals(check));
    }

    private Stream<Student> findStudentsByProperty(Collection<Student> students, Function<Student, String> function, String check, Comparator<Student> comparator) {
        return students
                .stream()
                .filter(student -> function.apply(student).equals(check))
                .sorted(comparator);
    }

    private static final Comparator<Student> STUDENT_COMPARATOR = Comparator
            .comparing(Student::getLastName).thenComparing(Student::getFirstName).reversed()
            .thenComparing(Student::compareTo);

    /**
     * Returns student {@link Student#getFirstName() first names}.
     *
     * @param students
     */
    @Override
    public List<String> getFirstNames(List<Student> students) {
        return getStudentProperty(students, Student::getFirstName)
                .collect(Collectors.toList());
    }

    /**
     * Returns student {@link Student#getLastName() last names}.
     *
     * @param students
     */
    @Override
    public List<String> getLastNames(List<Student> students) {
        return getStudentProperty(students, Student::getLastName)
                .collect(Collectors.toList());
    }

    /**
     * Returns student {@link Student#getGroup() groups}.
     *
     * @param students
     */
    @Override
    public List<GroupName> getGroups(List<Student> students) {
        return getStudentProperty(students, Student::getGroup)
                .collect(Collectors.toList());
    }

    /**
     * Returns full student name.
     *
     * @param students
     */
    @Override
    public List<String> getFullNames(List<Student> students) {
        return getStudentProperty(students, (student -> student.getFirstName() + " " + student.getLastName()))
                .collect(Collectors.toList());
    }

    /**
     * Returns distinct student {@link Student#getFirstName() first names} in lexicographic order.
     *
     * @param students
     */
    @Override
    public Set<String> getDistinctFirstNames(List<Student> students) {
        return getStudentProperty(students, Student::getFirstName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Returns a {@link Student#getFirstName() first name} of the student with maximal {@link Student#getId() id}.
     *
     * @param students
     */
    @Override
    public String getMaxStudentFirstName(List<Student> students) {
        return students
                .stream()
                .max(Student::compareTo)
                .map(Student::getFirstName).orElse("");
    }

    /**
     * Returns students ordered by {@link Student#getId() id}.
     *
     * @param students
     */
    @Override
    public List<Student> sortStudentsById(Collection<Student> students) {
        return sortStudentsByProperty(students, Student::compareTo);
    }

    /**
     * Returns students ordered by name.
     *
     * @param students
     */
    @Override
    public List<Student> sortStudentsByName(Collection<Student> students) {
        // :NOTE: create this comparator just once. Also replace "+" in favour of thenComparing
        return sortStudentsByProperty(students, STUDENT_COMPARATOR); //fixed
    }

    /**
     * Returns students having specified first name. Students are ordered by name.
     *
     * @param students
     * @param name
     */
    @Override
    public List<Student> findStudentsByFirstName(Collection<Student> students, String name) {
        // :NOTE: you close a stream only to open it again. Ineffective.
        return findStudentsByProperty(students, Student::getFirstName, name, STUDENT_COMPARATOR)
                .collect(Collectors.toList()); //fixed
    }

    /**
     * Returns students having specified last name. Students are ordered by name.
     *
     * @param students
     * @param name
     */
    @Override
    public List<Student> findStudentsByLastName(Collection<Student> students, String name) {
        return findStudentsByProperty(students, Student::getLastName, name).collect(Collectors.toList());
    }

    /**
     * Returns students having specified groups. Students are ordered by name.
     *
     * @param students
     * @param group
     */
    @Override
    public List<Student> findStudentsByGroup(Collection<Student> students, GroupName group) {
        // :NOTE: this function's implementation can be unified with the previous two
        return findStudentsByProperty(students, student -> student.getGroup().toString(), group.toString(), STUDENT_COMPARATOR) //fixed
                .collect(Collectors.toList());
    }

    /**
     * Returns map of group's student last names mapped to minimal first name.
     *
     * @param students
     * @param group
     */
    @Override
    public Map<String, String> findStudentNamesByGroup(Collection<Student> students, GroupName group) {
        return findStudentsByProperty(students, student -> student.getGroup().toString(), group.toString())
                // :NOTE: no need to sort here, you can just choose the lesser name in the collector
                .collect(Collectors.toMap(Student::getLastName, Student::getFirstName, //fixed
                        (existing, replacement) -> existing.compareTo(replacement) > 0 ? replacement : existing));
    }
}
