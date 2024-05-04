interface Expression{
    public abstract String toString1();
    public abstract int evaluate();
}
class Numeral implements Expression{
    private int value;
    public Numeral(int value){
        this.value=value;
    }
    @Override
    public String toString1(){
        return value+"";
    }
    @Override
    public int evaluate(){
        return value;
    }
}
class Square implements Expression{
    private Expression expression;
    public Square(Expression expression){
        this.expression=expression;
    }
    @Override
    public String toString1(){
        return expression.toString1()+"^2";
    }
    public int evaluate(){
        int a=expression.evaluate();
        return a*a;
    }
}
interface BinaryExpression extends Expression{
    public  abstract Expression left();
    public  abstract Expression right();

}
class Addition implements BinaryExpression{
    private Expression left;
    private Expression right;
    public Addition(Expression left, Expression right){
        this.left=left;
        this.right=right;
    }
    @Override
    public String toString1(){
        return left.toString1()+" + "+right.toString1();
    }
    @Override
    public int evaluate(){
        return left.evaluate()+right.evaluate();
    }
    @Override
    public Expression left(){
        return left;
    }
    public Expression right(){
        return right;
    }
}
class Subtraction implements BinaryExpression{
    private Expression left;
    private Expression right;
    public Subtraction (Expression left, Expression right){
        this.left=left;
        this.right=right;
    }
    @Override
    public String toString1(){
        return left.toString1()+" - "+right.toString1();
    }
    @Override
    public int evaluate(){
        return left.evaluate()-right.evaluate();
    }
    @Override
    public Expression left(){
        return left;
    }
    public Expression right(){
        return right;
    }
}
class Multiplication implements BinaryExpression{
    private Expression left;
    private Expression right;
    public Multiplication (Expression left, Expression right){
        this.left=left;
        this.right=right;
    }
    @Override
    public String toString1(){
        return left.toString1()+" * "+right.toString1();
    }
    @Override
    public int evaluate(){
        return left.evaluate()*right.evaluate();
    }
    @Override
    public Expression left(){
        return left;
    }
    public Expression right(){
        return right;
    }
}
public class Main {
    public static void main(String[] args) {
        Numeral one = new Numeral(1);
        Numeral three = new Numeral(3);
        Square threeSquare = new Square((three));
        Expression e = new Addition(one, threeSquare);
        Expression e1 = new Subtraction(one, threeSquare);
        Expression e2 = new Multiplication(one, threeSquare);
        System.out.println(e.toString1()+" = "+e.evaluate());
        System.out.println(e1.toString1()+" = "+e1.evaluate());
        System.out.println(e2.toString1()+" = "+e2.evaluate());
    }
}
