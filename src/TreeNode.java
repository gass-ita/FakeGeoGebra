public class TreeNode{
    private String data;
    public TreeNode left, right;

    public TreeNode(String data) {
        this.data = data;
        left = null;
        right = null;
    }

    

    public double evaluateTreeNode(TreeNode root) throws Exception{
        if(root == null) throw new NullPointerException();

        if(root.left == null && root.right == null) 
            try{
                return Double.parseDouble(root.data);
            } catch(NumberFormatException e){
                throw new RuntimeException("Invalid number in " + root);
            }

        
        double leftEvaluation = evaluateTreeNode(root.left);
        double rightEvaluation = evaluateTreeNode(root.right);

        if(root.data.equals("+")) return leftEvaluation + rightEvaluation;
        else if(root.data.equals("-")) return leftEvaluation - rightEvaluation;
        else if(root.data.equals("*")) return leftEvaluation * rightEvaluation;
        else if(root.data.equals("/")) return leftEvaluation / rightEvaluation;
        else if(root.data.equals("^")) return Math.pow(leftEvaluation, rightEvaluation);
        throw new RuntimeException("invalid data in " + root);
        
    }

    public double evaluateTreeNode() throws Exception{return evaluateTreeNode(this);}

    public boolean hasRDomain(TreeNode root) throws Exception{
        boolean hasRDomain = true;
        
        if(root == null) throw new NullPointerException();
        if(root.left == null && root.right == null) return true;
        
        //EXEPTION
        if(root.data.equals("/") && root.right.hasX()){
            hasRDomain = false;
        }
        
        



        if(!hasRDomain){
            System.out.println(root);
            return false;
        }
        
        return hasRDomain(root.left) && hasRDomain(root.right);
    }

    public boolean hasRDomain() throws Exception{return hasRDomain(this);}


    public boolean hasX(TreeNode root){
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

   
}
