package prog.expression.operation.checked;

import prog.expression.token.variable.Const;
import prog.expression.exceptions.ConstantOverflowException;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class CheckedConst {
   public static Const valueOf(String val, int idx) throws ConstantOverflowException {
       try {
           int number = Integer.parseInt(val);
           return new Const(number);
       } catch (NumberFormatException e) {
           throw new ConstantOverflowException(val, idx);
       }
   }
}
