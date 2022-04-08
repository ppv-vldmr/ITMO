package testLab4Calc;

import testLab4Calc.Token;
import static testLab4Calc.TokenType.*;

import java.util.*;
import java.util.function.Supplier;


import java.util.function.BiFunction;
import java.util.function.Function;



public class Parser {
    private final Supplier<Token> lexer;

    private Token token;

    

    public Parser(Supplier<Token> lexer) {
        this.lexer = lexer;
    }

    public Tree_fullExpression parse() {
        nextToken();
        var result = new Tree_fullExpression();
        result.parse();
        return result;
    }

        public class Tree_fullExpression implements Tree {
                    public Double result;

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
                            case LPAR:
                            case NUM:

                        {

                                    var child_0_0 = new Tree_expression(
                                    );
                                    child_0_0.parse();
                                    addChild(child_0_0);

                                    var expression = child_0_0;
    if (token.type() != EOF)
        throw new RuntimeException("Expected EOF, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            
    result = expression.result;


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_expression implements Tree {
                    public Double result;

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
                            case LPAR:
                            case NUM:

                        {

                                    var child_0_0 = new Tree_plusOperator(
                                    );
                                    child_0_0.parse();
                                    addChild(child_0_0);

                                    var plusOperator = child_0_0;

                            
    result = plusOperator.result;


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_plus implements Tree {
                    public BiFunction<Double,Double,Double> f;

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
                            case PLUS:

                        {

    if (token.type() != PLUS)
        throw new RuntimeException("Expected PLUS, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            
    f = (x, y) -> x + y;


                        }
                        break;
                            case MINUS:

                        {

    if (token.type() != MINUS)
        throw new RuntimeException("Expected MINUS, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            
    f = (x, y) -> x - y;


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_mul implements Tree {
                    public BiFunction<Double,Double,Double> f;

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
                            case DIV:

                        {

    if (token.type() != DIV)
        throw new RuntimeException("Expected DIV, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            
    f = (x, y) -> x / y;


                        }
                        break;
                            case MUL:

                        {

    if (token.type() != MUL)
        throw new RuntimeException("Expected MUL, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            
    f = (x, y) -> x * y;


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_fact implements Tree {
                    public Function<Double,Double> f;

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
                            case FACT:

                        {

    if (token.type() != FACT)
        throw new RuntimeException("Expected FACT, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            
    f = (x) -> {
        if (x == 1) {
            return 1.0;
        }
        return x * f.apply(x - 1);
    };


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_plusOperator implements Tree {
                    public Double result;

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
                            case LPAR:
                            case NUM:

                        {

                                    var child_0_0 = new Tree_mulOperator(
                                    );
                                    child_0_0.parse();
                                    addChild(child_0_0);

                                    var mulOperator = child_0_0;
                                    var child_0_1 = new Tree_plusOperatorCont_ZERO_OR_ONE1100334706(
                                                mulOperator.result
                                    );
                                    child_0_1.parse();
                                    addChild(child_0_1);

                                    var plusOperatorCont_ZERO_OR_ONE1100334706 = child_0_1;
                                            var plusOperatorCont = plusOperatorCont_ZERO_OR_ONE1100334706;


                            
    if (plusOperatorCont.result == null) {
        result = mulOperator.result;
    } else {
        result = plusOperatorCont.result;
    }


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_plusOperatorCont implements Tree {
                    Double acc;
                    public Double result;

            List<Tree> children = new ArrayList<>();

                Tree_plusOperatorCont(
                        Double acc
                ) {
                        this.acc = acc;
                }

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
                            case PLUS:
                            case MINUS:

                        {

                                    var child_0_0 = new Tree_plus(
                                    );
                                    child_0_0.parse();
                                    addChild(child_0_0);

                                    var plus = child_0_0;
                                    var child_0_1 = new Tree_mulOperator(
                                    );
                                    child_0_1.parse();
                                    addChild(child_0_1);

                                    var mulOperator = child_0_1;
                                    var child_0_2 = new Tree_plusOperatorCont_ZERO_OR_ONE707174529(
                                                plus.f.apply(acc, mulOperator.result)
                                    );
                                    child_0_2.parse();
                                    addChild(child_0_2);

                                    var plusOperatorCont_ZERO_OR_ONE707174529 = child_0_2;
                                            var plusOperatorCont = plusOperatorCont_ZERO_OR_ONE707174529;


                            
    if (plusOperatorCont.result == null) {
        result = plus.f.apply(acc, mulOperator.result);
    } else {
        result = plusOperatorCont.result;
    }


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_mulOperator implements Tree {
                    public Double result;

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
                            case LPAR:
                            case NUM:

                        {

                                    var child_0_0 = new Tree_factOperator(
                                    );
                                    child_0_0.parse();
                                    addChild(child_0_0);

                                    var factOperator = child_0_0;
                                    var child_0_1 = new Tree_mulOperatorCont_ZERO_OR_ONE1040661775(
                                    );
                                    child_0_1.parse();
                                    addChild(child_0_1);

                                    var mulOperatorCont_ZERO_OR_ONE1040661775 = child_0_1;
                                            var mulOperatorCont = mulOperatorCont_ZERO_OR_ONE1040661775;


                            
    if (mulOperatorCont.result == null) {
        result = factOperator.result;
    } else {
        result = mulOperatorCont.f.apply(factOperator.result, mulOperatorCont.result);
    }


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_mulOperatorCont implements Tree {
                    public BiFunction<Double,Double,Double> f;
                    public Double result;

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
                            case DIV:
                            case MUL:

                        {

                                    var child_0_0 = new Tree_mul(
                                    );
                                    child_0_0.parse();
                                    addChild(child_0_0);

                                    var mul = child_0_0;
                                    var child_0_1 = new Tree_factOperator(
                                    );
                                    child_0_1.parse();
                                    addChild(child_0_1);

                                    var factOperator = child_0_1;
                                    var child_0_2 = new Tree_mulOperatorCont_ZERO_OR_ONE76966900(
                                    );
                                    child_0_2.parse();
                                    addChild(child_0_2);

                                    var mulOperatorCont_ZERO_OR_ONE76966900 = child_0_2;
                                            var mulOperatorCont = mulOperatorCont_ZERO_OR_ONE76966900;


                            
    f = mul.f;

    if (mulOperatorCont.result == null) {
        result = factOperator.result;
    } else {
        result = mulOperatorCont.f.apply(factOperator.result, mulOperatorCont.result);
    }


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_factOperator implements Tree {
                    public Double result;

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
                            case LPAR:
                            case NUM:

                        {

                                    var child_0_0 = new Tree_term(
                                    );
                                    child_0_0.parse();
                                    addChild(child_0_0);

                                    var term = child_0_0;
                                    var child_0_1 = new Tree_factOperatorCont_ZERO_OR_ONE19962025(
                                                term.result
                                    );
                                    child_0_1.parse();
                                    addChild(child_0_1);

                                    var factOperatorCont_ZERO_OR_ONE19962025 = child_0_1;
                                            var factOperatorCont = factOperatorCont_ZERO_OR_ONE19962025;


                            
    if (factOperatorCont.result == null) {
        result = term.result;
    } else {
        result = factOperatorCont.result;
    }


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_term implements Tree {
                    public Double result;

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
                            case NUM:

                        {

    if (token.type() != NUM)
        throw new RuntimeException("Expected NUM, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            
    result = Double.parseDouble(content(0));


                        }
                        break;
                            case LPAR:

                        {

    if (token.type() != LPAR)
        throw new RuntimeException("Expected LPAR, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();
                                    var child_1_1 = new Tree_expression(
                                    );
                                    child_1_1.parse();
                                    addChild(child_1_1);

                                    var expression = child_1_1;
    if (token.type() != RPAR)
        throw new RuntimeException("Expected RPAR, but found " + token.type().name());
                                    addChild(token);
                                    nextToken();

                            
    result = expression.result;


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_factOperatorCont implements Tree {
                    Double acc;
                    public Double result;

            List<Tree> children = new ArrayList<>();

                Tree_factOperatorCont(
                        Double acc
                ) {
                        this.acc = acc;
                }

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
                            case FACT:

                        {

                                    var child_0_0 = new Tree_fact(
                                    );
                                    child_0_0.parse();
                                    addChild(child_0_0);

                                    var fact = child_0_0;
                                    var child_0_1 = new Tree_factOperatorCont_ZERO_OR_ONE661248853(
                                                fact.f.apply(acc)
                                    );
                                    child_0_1.parse();
                                    addChild(child_0_1);

                                    var factOperatorCont_ZERO_OR_ONE661248853 = child_0_1;
                                            var factOperatorCont = factOperatorCont_ZERO_OR_ONE661248853;


                            
    if (factOperatorCont.result == null) {
        result = fact.f.apply(acc);
    } else {
        result = factOperatorCont.result;
    }


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_plusOperatorCont_ZERO_OR_ONE1100334706 implements Tree {
                    Double acc;
                    public Double result;

            List<Tree> children = new ArrayList<>();

                Tree_plusOperatorCont_ZERO_OR_ONE1100334706(
                        Double acc
                ) {
                        this.acc = acc;
                }

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
                            case EOF:

                        {


                            

                        }
                        break;
                            case PLUS:
                            case MINUS:

                        {

                                    var child_1_0 = new Tree_plusOperatorCont(
                                                acc
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var plusOperatorCont = child_1_0;

                            result = plusOperatorCont.result;


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_plusOperatorCont_ZERO_OR_ONE707174529 implements Tree {
                    Double acc;
                    public Double result;

            List<Tree> children = new ArrayList<>();

                Tree_plusOperatorCont_ZERO_OR_ONE707174529(
                        Double acc
                ) {
                        this.acc = acc;
                }

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
                            case EOF:

                        {


                            

                        }
                        break;
                            case PLUS:
                            case MINUS:

                        {

                                    var child_1_0 = new Tree_plusOperatorCont(
                                                acc
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var plusOperatorCont = child_1_0;

                            result = plusOperatorCont.result;


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_mulOperatorCont_ZERO_OR_ONE1040661775 implements Tree {
                    public BiFunction<Double,Double,Double> f;
                    public Double result;

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
                            case EOF:
                            case PLUS:
                            case MINUS:

                        {


                            

                        }
                        break;
                            case DIV:
                            case MUL:

                        {

                                    var child_1_0 = new Tree_mulOperatorCont(
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var mulOperatorCont = child_1_0;

                            f = mulOperatorCont.f;
result = mulOperatorCont.result;


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_mulOperatorCont_ZERO_OR_ONE76966900 implements Tree {
                    public BiFunction<Double,Double,Double> f;
                    public Double result;

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
                            case EOF:
                            case PLUS:
                            case MINUS:

                        {


                            

                        }
                        break;
                            case DIV:
                            case MUL:

                        {

                                    var child_1_0 = new Tree_mulOperatorCont(
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var mulOperatorCont = child_1_0;

                            f = mulOperatorCont.f;
result = mulOperatorCont.result;


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_factOperatorCont_ZERO_OR_ONE19962025 implements Tree {
                    Double acc;
                    public Double result;

            List<Tree> children = new ArrayList<>();

                Tree_factOperatorCont_ZERO_OR_ONE19962025(
                        Double acc
                ) {
                        this.acc = acc;
                }

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
                            case DIV:
                            case MUL:
                            case RPAR:
                            case EOF:
                            case PLUS:
                            case MINUS:

                        {


                            

                        }
                        break;
                            case FACT:

                        {

                                    var child_1_0 = new Tree_factOperatorCont(
                                                acc
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var factOperatorCont = child_1_0;

                            result = factOperatorCont.result;


                        }
                        break;

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

        public class Tree_factOperatorCont_ZERO_OR_ONE661248853 implements Tree {
                    Double acc;
                    public Double result;

            List<Tree> children = new ArrayList<>();

                Tree_factOperatorCont_ZERO_OR_ONE661248853(
                        Double acc
                ) {
                        this.acc = acc;
                }

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
                            case DIV:
                            case MUL:
                            case RPAR:
                            case EOF:
                            case PLUS:
                            case MINUS:

                        {


                            

                        }
                        break;
                            case FACT:

                        {

                                    var child_1_0 = new Tree_factOperatorCont(
                                                acc
                                    );
                                    child_1_0.parse();
                                    addChild(child_1_0);

                                    var factOperatorCont = child_1_0;

                            result = factOperatorCont.result;


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
