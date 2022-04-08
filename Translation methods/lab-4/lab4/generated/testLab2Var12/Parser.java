package testLab2Var12;

import testLab2Var12.Token;
import static testLab2Var12.TokenType.*;

import java.util.*;
import java.util.function.Supplier;




public class Parser {
    private final Supplier<Token> lexer;

    private Token token;

    

    public Parser(Supplier<Token> lexer) {
        this.lexer = lexer;
    }

    public Tree_forLoop parse() {
        nextToken();
        var result = new Tree_forLoop();
        result.parse();
        return result;
    }

        public class Tree_forLoop implements Tree {

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
                            case FOR:

                        {

    if (token.type() != FOR)
        throw new RuntimeException("Expected FOR, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
    if (token.type() != LPAR)
        throw new RuntimeException("Expected LPAR, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
                                    var child_0_2 = new Tree_init_ZERO_OR_ONE1100249452(
                                    );
                                    child_0_2.parse();
                                    addChild(child_0_2);

                                    var init_ZERO_OR_ONE1100249452 = child_0_2;
                                            var init2 = init_ZERO_OR_ONE1100249452;

    if (token.type() != SEMICOLON)
        throw new RuntimeException("Expected SEMICOLON, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
                                    var child_0_4 = new Tree_cond_ZERO_OR_ONE87108567(
                                    );
                                    child_0_4.parse();
                                    addChild(child_0_4);

                                    var cond_ZERO_OR_ONE87108567 = child_0_4;
                                            var cond4 = cond_ZERO_OR_ONE87108567;

    if (token.type() != SEMICOLON)
        throw new RuntimeException("Expected SEMICOLON, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
                                    var child_0_6 = new Tree_inc_ZERO_OR_ONE1779071669(
                                    );
                                    child_0_6.parse();
                                    addChild(child_0_6);

                                    var inc_ZERO_OR_ONE1779071669 = child_0_6;
                                            var inc6 = inc_ZERO_OR_ONE1779071669;

    if (token.type() != RPAR)
        throw new RuntimeException("Expected RPAR, but found " + token.type().name());
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

        public class Tree_init implements Tree {

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
                            case TYPE:

                        {

    if (token.type() != TYPE)
        throw new RuntimeException("Expected TYPE, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
    if (token.type() != VAR)
        throw new RuntimeException("Expected VAR, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
    if (token.type() != EQUALS)
        throw new RuntimeException("Expected EQUALS, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
    if (token.type() != VALUE)
        throw new RuntimeException("Expected VALUE, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_cond implements Tree {

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
                            case VAR:

                        {

    if (token.type() != VAR)
        throw new RuntimeException("Expected VAR, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
    if (token.type() != COMPARE)
        throw new RuntimeException("Expected COMPARE, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
    if (token.type() != VALUE)
        throw new RuntimeException("Expected VALUE, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_inc implements Tree {

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
                            case VAR:

                        {

    if (token.type() != VAR)
        throw new RuntimeException("Expected VAR, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
    if (token.type() != INC)
        throw new RuntimeException("Expected INC, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            

                        }
                        break;
                            case INC:

                        {

    if (token.type() != INC)
        throw new RuntimeException("Expected INC, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
    if (token.type() != VAR)
        throw new RuntimeException("Expected VAR, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_init_ZERO_OR_ONE1100249452 implements Tree {

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
                            case SEMICOLON:

                        {


                            

                        }
                        break;
                            case TYPE:

                        {

                                    var child_1_0 = new Tree_init(
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var init = child_1_0;

                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_cond_ZERO_OR_ONE87108567 implements Tree {

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
                            case SEMICOLON:

                        {


                            

                        }
                        break;
                            case VAR:

                        {

                                    var child_1_0 = new Tree_cond(
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var cond = child_1_0;

                            

                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_inc_ZERO_OR_ONE1779071669 implements Tree {

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
                            case VAR:
                            case INC:

                        {

                                    var child_1_0 = new Tree_inc(
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var inc = child_1_0;

                            

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
