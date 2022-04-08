package kek.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record KekFile(KekContextManager contextManager) implements Unit {
    private static final String inputAndOutput = """
            #ifndef KEK
            #define KEK
                        
            #include <stdio.h>
            #include <stdlib.h>
            #include <stdbool.h>
                        
            long long readint() {
                long long x;
                scanf("%lld", &x);
                return x;
            }
                        
            bool readbool() {
                long long x = readint();
                return x != 0;
            }
                        
            bool print(long long x) {
                printf("%lld", x);
                return true;
            }
                        
            bool printspace() {
                printf(" ");
                return true;
            }
                        
                        
            bool println() {
                printf("\\n");
                return true;
            }
                        
            #endif
            """;

    @Override
    public List<String> getC() {
        List<String> result = new ArrayList<>();
        result.add(inputAndOutput);
        result.add("");

        for (KekVar var : contextManager.getGlobalVars()) {
            result.add(var.getCString() + ";");
        }
        for (KekFunc func : contextManager.getGlobalFuncs()) {
            result.add(func.getCString() + ";");
        }

        Map<String, Block> funcsContent = contextManager.getGlobalContext().getFuncsContent();
        for (KekFunc func : contextManager.getGlobalFuncs()) {
            Block kekBlock = funcsContent.get(func.name());
            if (kekBlock == null)
                throw new RuntimeException("Could not find definition of the function '" + func.name() + "'");
            result.add(func.getCString());
            result.addAll(kekBlock.getC());
        }

        return result;
    }
}
