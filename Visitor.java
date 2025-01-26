import minipython.analysis.*;
import minipython.node.*;
import java.util.*;

public class Visitor extends DepthFirstAdapter
{
    private static Hashtable symtable;
    public static int errors;

    Visitor(Hashtable symtable)
    {
        this.symtable = symtable;
        errors = 0;
    }

    public static Hashtable getSymtable() {
        return symtable;
    }

    /** Check if function is already defined */
    @Override
    public void inAFunction(AFunction node) {
        String fName = node.getId().toString().trim();

        if(symtable.containsKey(fName)){

            LinkedList node_arguments = node.getArgument();
            AFunction same_name_function = (AFunction)symtable.get(fName);
            LinkedList other_function_arguments = same_name_function.getArgument();

            // check number of arguments for node
            int node_default_args = 0;
            int node_max_args = 0;
            if(node_arguments.size() == 1) {

                AArgument argument = (AArgument)node_arguments.get(0);
                LinkedList assigns_value = argument.getAssignValue();

                if(assigns_value.size() == 1){
                    node_default_args++;
                }

                LinkedList comma_assigns = argument.getCommaAssign();

                for(int j =0;j< comma_assigns.size();j++){
                    ACommaAssign comma_assign = (ACommaAssign) comma_assigns.get(j);
                    LinkedList default_values = comma_assign.getAssignValue();

                    if(default_values.size() == 1){
                        node_default_args++;
                    }
                }

                node_max_args = comma_assigns.size() + 1;
            }

            // check number of arguments for other function
            int other_function_default_args = 0;
            int other_function_max_args = 0;
            if(other_function_arguments.size() == 1) {

                AArgument argument = (AArgument)other_function_arguments.get(0);
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

            if(node_max_args - node_default_args == other_function_max_args || other_function_max_args - other_function_default_args == node_max_args ){
                // minimum arguments of one function == maximum arguments of another function
                int line = ((TId)node.getId()).getLine();
                int position = ((TId)node.getId()).getPos();
                System.out.println("[line : " + line + ", position : " + position + "] :" + " Function " + fName + " is already defined");
                errors++;

            }

        }else{
            symtable.put(fName, node);
            LinkedList node_arguments = node.getArgument();
            if(node_arguments.size() == 1){
                AArgument argument = (AArgument)node_arguments.get(0);
                String argName = argument.getId().toString().trim();
                symtable.put(argName,argument);
            }


        }
    }

    /** Add the rest of the function arguments to the symbol table */
    @Override
    public void inACommaAssign(ACommaAssign node) {
        String arg_name = node.getId().toString().trim(); //get name of the argument
        if(!symtable.containsKey(arg_name)){
            symtable.put(arg_name,node);
        }
    }

    /** add the variable to the symbol table */
    @Override
    public void inAAssignStatement(AAssignStatement node) {
        String varName = node.getId().toString().trim();
        symtable.put(varName, node);
    }

    /** add the node and type of value of the expression to the out Hashtable */
    @Override
    public void outAAssignStatement(AAssignStatement node) {
        PValue type = (PValue) getOut(node.getExpression());
        setOut(node, type);
    }

    /** Check if a variable is used but not defined in an expression */
    @Override
    public void inAIdentifierExpression(AIdentifierExpression node) {
        String varName = node.getId().toString().trim();
        int line = ((TId)node.getId()).getLine();
        int position = ((TId)node.getId()).getPos();

        if(!symtable.containsKey(varName)){
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Variable " + varName + " is not defined");
            errors++;
        }
    }

    /** Check if the variable is used but not defined in an assign min statement */
    @Override
    public void inAAssignMinStatement(AAssignMinStatement node) {
        String varName = node.getId().toString().trim();
        int line = ((TId)node.getId()).getLine();
        int position = ((TId)node.getId()).getPos();

        if(!symtable.containsKey(varName)){
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Variable " + varName + " is not defined");
            errors++;
        }
    }

    /** Check if the variable is used but not defined in an assign div statement */
    @Override
    public void inAAssignDivStatement(AAssignDivStatement node) {
        String varName = node.getId().toString().trim();
        int line = ((TId)node.getId()).getLine();
        int position = ((TId)node.getId()).getPos();

        if(!symtable.containsKey(varName)){
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Variable " + varName + " is not defined");
            errors++;
        }
    }

    /** Check if the variable is used but not defined in a list assignment statement */
    @Override
    public void inAListAssignmentStatement(AListAssignmentStatement node) {
        String varName = node.getId().toString().trim();
        int line = ((TId)node.getId()).getLine();
        int position = ((TId)node.getId()).getPos();

        if(!symtable.containsKey(varName)){
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Variable " + varName + " is not defined");
            errors++;
        }
    }

    /** Check if there's an error in additions */
    @Override
    public void inAPlusExpression(APlusExpression node) {
        int line,position;
        PValue value1 = null;
        PValue value2 = null;

        if(node.getLpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getLpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getLpar().toString().trim());
                value1 = (PValue) getOut(nodeEq);
            }
        }else{
            value1 = ((AValueExpression) node.getLpar()).getValue();
        }

        if(node.getRpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getRpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getRpar().toString().trim());
                value2 = (PValue) getOut(nodeEq);
            }
        }else{
            value2 = ((AValueExpression) node.getRpar()).getValue();
        }


        if(value1 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value1).getNone().getLine();
            position = ((ANoneValueValue) value1).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if( value2 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value2).getNone().getLine();
            position = ((ANoneValueValue) value2).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if (value1 instanceof AStringValueValue  && value2 instanceof ANumberValueValue) {
            line = ((AStringValueValue) value1).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        } else if (value1 instanceof  ANumberValueValue && value2 instanceof AStringValueValue) {
            line = ((AStringValueValue) value2).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        }

    }


    /** Check if there's an error in subtractions */
    @Override
    public void inAMinusExpression(AMinusExpression node) {
        int line,position;
        PValue value1 = null;
        PValue value2 = null;

        if(node.getLpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getLpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getLpar().toString().trim());
                value1 = (PValue) getOut(nodeEq);
            }
        }else{
            value1 = ((AValueExpression) node.getLpar()).getValue();
        }

        if(node.getRpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getRpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getRpar().toString().trim());
                value2 = (PValue) getOut(nodeEq);
            }
        }else{
            value2 = ((AValueExpression) node.getRpar()).getValue();
        }


        if(value1 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value1).getNone().getLine();
            position = ((ANoneValueValue) value1).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if( value2 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value2).getNone().getLine();
            position = ((ANoneValueValue) value2).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if (value1 instanceof AStringValueValue  && value2 instanceof ANumberValueValue) {
            line = ((AStringValueValue) value1).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        } else if (value1 instanceof  ANumberValueValue && value2 instanceof AStringValueValue) {
            line = ((AStringValueValue) value2).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        }
    }

    /** Check if there's an error in multiplications */
    @Override
    public void inAMultExpression(AMultExpression node) {
        int line,position;
        PValue value1 = null;
        PValue value2 = null;

        if(node.getLpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getLpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getLpar().toString().trim());
                value1 = (PValue) getOut(nodeEq);
            }
        }else{
            value1 = ((AValueExpression) node.getLpar()).getValue();
        }

        if(node.getRpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getRpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getRpar().toString().trim());
                value2 = (PValue) getOut(nodeEq);
            }
        }else{
            value2 = ((AValueExpression) node.getRpar()).getValue();
        }


        if(value1 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value1).getNone().getLine();
            position = ((ANoneValueValue) value1).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if( value2 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value2).getNone().getLine();
            position = ((ANoneValueValue) value2).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if (value1 instanceof AStringValueValue  && value2 instanceof ANumberValueValue) {
            line = ((AStringValueValue) value1).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        } else if (value1 instanceof  ANumberValueValue && value2 instanceof AStringValueValue) {
            line = ((AStringValueValue) value2).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        }
    }

    /** Check if there's an error in power expressions */
    @Override
    public void inAPowerExpression(APowerExpression node) {
        int line,position;
        PValue value1 = null;
        PValue value2 = null;

        if(node.getLpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getLpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getLpar().toString().trim());
                value1 = (PValue) getOut(nodeEq);
            }
        }else{
            value1 = ((AValueExpression) node.getLpar()).getValue();
        }

        if(node.getRpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getRpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getRpar().toString().trim());
                value2 = (PValue) getOut(nodeEq);
            }
        }else{
            value2 = ((AValueExpression) node.getRpar()).getValue();
        }


        if(value1 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value1).getNone().getLine();
            position = ((ANoneValueValue) value1).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if( value2 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value2).getNone().getLine();
            position = ((ANoneValueValue) value2).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if (value1 instanceof AStringValueValue  && value2 instanceof ANumberValueValue) {
            line = ((AStringValueValue) value1).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        } else if (value1 instanceof  ANumberValueValue && value2 instanceof AStringValueValue) {
            line = ((AStringValueValue) value2).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        }
    }

    /** Check if there's an error in divisions */
    @Override
    public void inADivExpression(ADivExpression node) {
        int line,position;
        PValue value1 = null;
        PValue value2 = null;

        if(node.getLpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getLpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getLpar().toString().trim());
                value1 = (PValue) getOut(nodeEq);
            }
        }else{
            value1 = ((AValueExpression) node.getLpar()).getValue();
        }

        if(node.getRpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getRpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getRpar().toString().trim());
                value2 = (PValue) getOut(nodeEq);
            }
        }else{
            value2 = ((AValueExpression) node.getRpar()).getValue();
        }


        if(value1 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value1).getNone().getLine();
            position = ((ANoneValueValue) value1).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if( value2 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value2).getNone().getLine();
            position = ((ANoneValueValue) value2).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if (value1 instanceof AStringValueValue  && value2 instanceof ANumberValueValue) {
            line = ((AStringValueValue) value1).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        } else if (value1 instanceof  ANumberValueValue && value2 instanceof AStringValueValue) {
            line = ((AStringValueValue) value2).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        }
    }

    /** Check if there's an error in modulo operations */
    @Override
    public void inAModExpression(AModExpression node) {
        int line,position;
        PValue value1 = null;
        PValue value2 = null;

        if(node.getLpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getLpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getLpar().toString().trim());
                value1 = (PValue) getOut(nodeEq);
            }
        }else{
            value1 = ((AValueExpression) node.getLpar()).getValue();
        }

        if(node.getRpar() instanceof AIdentifierExpression){
            if (symtable.get(node.getRpar().toString().trim()) instanceof AAssignStatement){
                // has been assigned a value before
                AAssignStatement nodeEq = (AAssignStatement) symtable.get(node.getRpar().toString().trim());
                value2 = (PValue) getOut(nodeEq);
            }
        }else{
            value2 = ((AValueExpression) node.getRpar()).getValue();
        }


        if(value1 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value1).getNone().getLine();
            position = ((ANoneValueValue) value1).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if( value2 instanceof ANoneValueValue) {
            line = ((ANoneValueValue) value2).getNone().getLine();
            position = ((ANoneValueValue) value2).getNone().getPos();
            System.out.println("[line : " + line + ", position : " + position + "] :" + " Cannot perform addition operation with None");
            errors++;
        } else if (value1 instanceof AStringValueValue  && value2 instanceof ANumberValueValue) {
            line = ((AStringValueValue) value1).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        } else if (value1 instanceof  ANumberValueValue && value2 instanceof AStringValueValue) {
            line = ((AStringValueValue) value2).getString().getLine();
            System.out.println("[line : "+ line + "] :" + " Cannot perform addition operation with Number and String");
            errors++;
        }
    }

    @Override
    public void outANoneValueValue(ANoneValueValue node) {
        setOut(node,node);
    }

    @Override
    public void outANumberValueValue(ANumberValueValue node){
        setOut(node, node);
    }

    @Override
    public void outAValueExpression(AValueExpression node){
        PValue type = (PValue) getOut(node.getValue());
        setOut(node, type);
    }

    @Override
    public void outAStringValueValue(AStringValueValue node){
        setOut(node, node);
    }


    /** Check if identifiers exist in for statement */
    @Override
    public void inAForStatement(AForStatement node) {
        String varName1 = node.getForId().toString().trim();
        String varName2 = node.getInId().toString().trim();
        if (!symtable.containsKey(varName2)) {
            int line = ((TId) node.getInId()).getLine();
            int position = ((TId) node.getInId()).getPos();
            System.out.println("[line : "+ line + " position : " + position + "] :" + " variable " + varName2 + "is not defined.");
        }else{
            if (!symtable.containsKey(varName1)) {
                symtable.put(varName1, node);
            }
        }
    }

}