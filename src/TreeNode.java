public class TreeNode{
    private static final String[] OPERATORS = {"+", "-", "*", "/", "^"};
    private static final String[] FUNCTIONS1 = {"exp", "ln", "sin", "cos", "tan"};
    private static final String[] FUNCTIONS2 = {"log"};

    private String data;
    public TreeNode left, right;

    public TreeNode(String data) {
        this.data = data;
        left = null;
        right = null;
    }

    public TreeNode(String data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    

    public static double evaluateTreeNode(TreeNode root) throws Exception{
        if(root == null) throw new NullPointerException();

        if(root.left == null && root.right == null) 
            try{
                return Double.parseDouble(root.data);
            } catch(NumberFormatException e){
                throw new RuntimeException("Invalid number in " + root);
            }

        double leftEvaluation = 0, rightEvaluation = 0;
        try{ leftEvaluation = evaluateTreeNode(root.left);} catch(Exception e){leftEvaluation = 0;}
        try{ rightEvaluation = evaluateTreeNode(root.right);} catch(Exception e){leftEvaluation = 0;}

        if(root.data.equals("+")) return leftEvaluation + rightEvaluation;
        else if(root.data.equals("-")) return leftEvaluation - rightEvaluation;
        else if(root.data.equals("*")) return leftEvaluation * rightEvaluation;
        else if(root.data.equals("/")) return leftEvaluation / rightEvaluation;
        else if(root.data.equals("^")) return Math.pow(leftEvaluation, rightEvaluation);
        else if(root.data.equals("exp")) return Math.exp(rightEvaluation);
        else if(root.data.equals("ln")) return Math.log(rightEvaluation);
        else if(root.data.equals("sin")) return Math.sin(rightEvaluation);
        else if(root.data.equals("cos")) return Math.cos(rightEvaluation);
        else if(root.data.equals("tan")) return Math.tan(rightEvaluation);
        else if(root.data.equals("log")) return Math.log(rightEvaluation) / Math.log(leftEvaluation);
        else
        throw new RuntimeException("invalid data in " + root);
        
    }

    public double evaluateTreeNode() throws Exception{return evaluateTreeNode(this);}

    public static boolean hasRDomain(TreeNode root) throws Exception{
        boolean hasRDomain = true;
        
        if(root == null) throw new NullPointerException();
        if(root.left == null && root.right == null) return true;
        
        //EXEPTION
        if(root.data.equals("/") && root.right.hasX()){
            System.out.println(root.right + " has x");
            hasRDomain = false;
        }

        if(!hasRDomain){
            return false;
        }
        
        return hasRDomain(root.left) && hasRDomain(root.right);
    }

    public boolean hasRDomain() throws Exception{return hasRDomain(this);}


    public static boolean hasX(TreeNode root){
        if(root == null) throw new NullPointerException();
        if(root.left == null && root.right == null) 
            return root.data.equals("x");
        return hasX(root.left) || hasX(root.right);
    }

    public boolean hasX(){return hasX(this);}


    @Override
    public String toString() {
        String s;
        s = data;
        if(this.right != null) s =  s + " " + right;
        if(this.left != null)  s = left + " " + s;
        s = "(" + s + ")";
        return s;
    }

    public static TreeNode fromString(String s){
        if(s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')') throw new RuntimeException("Invalid string");
        //remove the first and last character
        s = s.substring(1, s.length() - 1);
        
        String operator = "";
        String left = "";
        String right = "";

        int i = 0;
        int count = 0;

        String[] stringArr = s.split(" ");

        
        while(i < stringArr.length){
            if(stringArr[i] == "(") count++;
            else if (stringArr[i] == ")") count--;
            else if(count == 0) 
            {
                for(String op : OPERATORS){
                    if(stringArr[i].equals(op)){
                        operator = op;
                        left = s.substring(1, s.indexOf(op) - 1);
                        right = s.substring(s.indexOf(op) + op.length() + 1, s.length() - 1);
                        break;
                    }
                }

                for(String func : FUNCTIONS1){
                    if(stringArr[i].equals(func)){
                        operator = func;
                        right = s.substring(s.indexOf(func) + func.length() + 1, s.length() - 1);
                        break;
                    }
                }

                for(String func : FUNCTIONS2){
                    if(stringArr[i].equals(func)){
                        operator = func;
                        left = s.substring(1, s.indexOf(func) - 1);
                        right = s.substring(s.indexOf(func) + func.length() + 1, s.length() - 1);
                        break;
                    }
                }
            }

            i++;
        }

        if(operator.equals("")) return new TreeNode(s);
        else if(left.equals("")) return new TreeNode(operator, null, fromString(right));
        else return new TreeNode(operator, fromString(left), fromString(right));        
    }

   
}
