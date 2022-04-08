package testLab2Var10;

import testLab2Var10.Token;
import static testLab2Var10.TokenType.*;

import java.util.*;
import java.util.function.Supplier;




public class Parser {
    private final Supplier<Token> lexer;

    private Token token;

    

    public Parser(Supplier<Token> lexer) {
        this.lexer = lexer;
    }

    public Tree_func parse() {
        nextToken();
        var result = new Tree_func();
        result.parse();
        return result;
    }

        public class Tree_func implements Tree {

            List<Tree> children = new ArrayList<>();


            public List<Tree> getChildren() {
                return children;
            }

            void addChild(Tree tree) {
                children.add(tree);
            }

            String content(int i) {
                return ((Token) children.get(i)).content();
            }

            void parse() {
                switch (token.type()) {
                            case TYPE_NAME:

                        {

                                    var child_0_0 = new Tree_type(
                                    );
                                    child_0_0.parse();
                                    addChild(child_0_0);

                                    var type = child_0_0;
    if (token.type() != LPAR)
        throw new RuntimeException("Expected LPAR, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
                                    var child_0_2 = new Tree_argument_ZERO_OR_ONE1314037599(
                                    );
                                    child_0_2.parse();
                                    addChild(child_0_2);

                                    var argument_ZERO_OR_ONE1314037599 = child_0_2;
                                            var argument2 = argument_ZERO_OR_ONE1314037599;

                                    var child_0_3 = new Tree_func_inner1397074268_ZERO_OR_MORE1831436193(
                                    );
                                    child_0_3.parse();
                                    addChild(child_0_3);

                                    var func_inner1397074268_ZERO_OR_MORE1831436193 = child_0_3;
                                            var func3 = func_inner1397074268_ZERO_OR_MORE1831436193;

    if (token.type() != RPAR)
        throw new RuntimeException("Expected RPAR, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
    if (token.type() != SEMICOLON)
        throw new RuntimeException("Expected SEMICOLON, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
    if (token.type() != EOF)
        throw new RuntimeException("Expected EOF, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_type implements Tree {

            List<Tree> children = new ArrayList<>();


            public List<Tree> getChildren() {
                return children;
            }

            void addChild(Tree tree) {
                children.add(tree);
            }

            String content(int i) {
                return ((Token) children.get(i)).content();
            }

            void parse() {
                switch (token.type()) {
                            case TYPE_NAME:

                        {

    if (token.type() != TYPE_NAME)
        throw new RuntimeException("Expected TYPE_NAME, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
                                    var child_0_1 = new Tree_POINTER_ZERO_OR_MORE1377152697(
                                    );
                                    child_0_1.parse();
                                    addChild(child_0_1);

                                    var POINTER_ZERO_OR_MORE1377152697 = child_0_1;
                                            var POINTER1 = POINTER_ZERO_OR_MORE1377152697;

    if (token.type() != NAME)
        throw new RuntimeException("Expected NAME, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_argument implements Tree {

            List<Tree> children = new ArrayList<>();


            public List<Tree> getChildren() {
                return children;
            }

            void addChild(Tree tree) {
                children.add(tree);
            }

            String content(int i) {
                return ((Token) children.get(i)).content();
            }

            void parse() {
                switch (token.type()) {
                            case VALUE:

                        {

    if (token.type() != VALUE)
        throw new RuntimeException("Expected VALUE, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            

                        }
                        break;
                            case TYPE_NAME:

                        {

                                    var child_1_0 = new Tree_type(
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var type = child_1_0;

                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_argument_ZERO_OR_ONE1314037599 implements Tree {

            List<Tree> children = new ArrayList<>();


            public List<Tree> getChildren() {
                return children;
            }

            void addChild(Tree tree) {
                children.add(tree);
            }

            String content(int i) {
                return ((Token) children.get(i)).content();
            }

            void parse() {
                switch (token.type()) {
                            case COMMA:
                            case RPAR:

                        {


                            

                        }
                        break;
                            case VALUE:
                            case TYPE_NAME:

                        {

                                    var child_1_0 = new Tree_argument(
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var argument = child_1_0;

                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_func_inner1397074268 implements Tree {

            List<Tree> children = new ArrayList<>();


            public List<Tree> getChildren() {
                return children;
            }

            void addChild(Tree tree) {
                children.add(tree);
            }

            String content(int i) {
                return ((Token) children.get(i)).content();
            }

            void parse() {
                switch (token.type()) {
                            case COMMA:

                        {

    if (token.type() != COMMA)
        throw new RuntimeException("Expected COMMA, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
                                    var child_0_1 = new Tree_argument(
                                    );
                                    child_0_1.parse();
                                    addChild(child_0_1);

                                    var argument = child_0_1;

                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_func_inner1397074268_ZERO_OR_MORE1831436193 implements Tree {

            List<Tree> children = new ArrayList<>();


            public List<Tree> getChildren() {
                return children;
            }

            void addChild(Tree tree) {
                children.add(tree);
            }

            String content(int i) {
                return ((Token) children.get(i)).content();
            }

            void parse() {
                switch (token.type()) {
                            case RPAR:

                        {


                            

                        }
                        break;
                            case COMMA:

                        {

                                    var child_1_0 = new Tree_func_inner1397074268(
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var func_inner1397074268 = child_1_0;
                                            var func0 = func_inner1397074268;

                                    var child_1_1 = new Tree_func_inner1397074268_ZERO_OR_MORE1831436193(
                                    );
                                    child_1_1.parse();
                                    addChild(child_1_1);

                                    var func_inner1397074268_ZERO_OR_MORE1831436193 = child_1_1;
                                            var func1 = func_inner1397074268_ZERO_OR_MORE1831436193;


                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_POINTER_ZERO_OR_MORE1377152697 implements Tree {

            List<Tree> children = new ArrayList<>();


            public List<Tree> getChildren() {
                return children;
            }

            void addChild(Tree tree) {
                children.add(tree);
            }

            String content(int i) {
                return ((Token) children.get(i)).content();
            }

            void parse() {
                switch (token.type()) {
                            case NAME:

                        {


                            

                        }
                        break;
                            case POINTER:

                        {

    if (token.type() != POINTER)
        throw new RuntimeException("Expected POINTER, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
                                    var child_1_1 = new Tree_POINTER_ZERO_OR_MORE1377152697(
                                    );
                                    child_1_1.parse();
                                    addChild(child_1_1);

                                    var POINTER_ZERO_OR_MORE1377152697 = child_1_1;
                                            var POINTER1 = POINTER_ZERO_OR_MORE1377152697;


                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }


    private void nextToken() {
        token = lexer.get();
    }
}
