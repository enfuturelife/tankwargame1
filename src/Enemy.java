import java.awt.*;
import java.util.Random;

public class Enemy extends Tank{

    int movetime=0;

    public Enemy(String img, int x, int y, String upImage, String downImage, String leftImage, String rightImage, GamePanel gamePanel) {
        super(img, x, y, upImage, downImage, leftImage, rightImage, gamePanel);
    }

    @Override
    public void paintSelf(Graphics g) {

        g.drawImage(img,x,y,null);
        go();
    }

    public void go(){
        attack();
        if(movetime>=10)
        {
            direction=getRandomDirection();
            movetime=0;
        }
        else{movetime++;}
        switch(direction) {
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
            case RIGHT:
                rightward();
                break;
            case LEFT:
                leftward();
                break;
        }
    }

    public Direction getRandomDirection(){
        Random random=new Random();
        int x=random.nextInt(4);
        switch (x){
            case 0:return Direction.UP;
            case 1:return Direction.LEFT;
            case 2:return Direction.DOWN;
            case 3:return Direction.RIGHT;
            default:return null;
        }

    }

    public void attack()
    {
        Point p=this.getHeadPoint();
        Random random=new Random();
        int a=random.nextInt(200);
        if(a<6)
        {
            this.gamePanel.bulletList.add(new EneryBullet("images/bulletRed.gif",p.x,p.y,direction,this.gamePanel));
        }

    }


    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,width,height);
    }
}
