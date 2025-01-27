import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

import java.util.Hashtable;
import java.util.LinkedList;

public class Visitor2  extends DepthFirstAdapter {

    private Hashtable symtable;
    public static int errors;

    Visitor2(Hashtable symtable)
    {
        this.symtable = symtable;
        errors = 0;
    }

    /** Check function call arguments */
    @Override
    public void inAFunctionCall(AFunctionCall node) {
        String fName = node.getId().toString().trim();
        int line = ((TId)node.getId()).getLine();
        int position = ((TId)node.getId()).getPos();

        if(Visitor.symtable.containsKey(fName)){
            LinkedList node_arguments = node.getArglist();
            AFunction function = (AFunction)symtable.get(fName);
            LinkedList function_arguments = function.getArgument();

            // check number of arguments for node
            int node_max_args = 0;
            if(node_arguments.size() == 1) {
                node_max_args++;

                AArglist argument = (AArglist) node_arguments.get(0);
                LinkedList commaExpression = argument.getCommaExpression();

                node_max_args+=commaExpression.size();
            }

            // check number of arguments for other function
            int other_function_default_args = 0;
            int other_function_max_args = 0;
            if(function_arguments.size() == 1) {

                AArgument argument = (AArgument)function_arguments.get(0);
                LinkedList assigns_value = argument.getAssignValue();

                if(assigns_value.size() == 1){
                    other_function_default_args++;
                }

                LinkedList comma_assigns = argument.getCommaAssign();

                for(int j =0;j< comma_assigns.size();j++){
                    ACommaAssign comma_assign = (ACommaAssign) comma_assigns.get(j);
                    LinkedList default_values = comma_assign.getAssignValue();

                    if(default_values.size() == 1){
                        other_function_default_args++;
                    }
                }

                other_function_max_args = comma_assigns.size() + 1;
            }

            if(other_function_max_args - other_function_default_args != node_max_args || other_function_max_args != node_max_args ){
                System.out.println("[line : " + line + ", position : " + position + "] :" + " Function " + fName + " is does not have proper number of arguments");
                errors++;
            }

        } else {
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Function " + fName + " is not defined");
            errors++;
        }
    }

}
