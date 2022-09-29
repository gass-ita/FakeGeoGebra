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

        

        if(!hasRDomain(root)) throw new RuntimeException();

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
        
        double leftEvaluation = evaluateTreeNode(root.left);
        double rightEvaluation = evaluateTreeNode(root.right);
        //EXEPTION
        if(root.data.equals("/") && leftEvaluation == 0){
            hasRDomain = false;
        }
        
        



        if(!hasRDomain){
            System.out.println(root);
            return false;
        }
        
        return hasRDomain(root.left) && hasRDomain(root.right);
    }





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
