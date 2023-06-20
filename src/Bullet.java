import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bullet extends GameObject {
    int width=10;
    int hight=10;
    int speed=10;

    Direction direction;

    public Bullet(String img, int x, int y, Direction direction,GamePanel gamePanel) {
        super(img, x, y, gamePanel);
        this.direction=direction;
    }

    public void leftward(){
        x -= speed;

    }
    public void rightward(){
        x += speed;

    }
    public void upward(){
        y -= speed;

    }
    public void downward(){

        y += speed;

    }


    public void go()
    {
        switch(direction)
        {
            case LEFT :leftward();break;
            case RIGHT: rightward();break;
            case UP: upward();break;
            case DOWN: downward();break;

        }
    }

    public  void hitEne()
    {

        ArrayList<Enemy> enemies = this.gamePanel.eneList;
        for(Enemy enemy:enemies)
        {
            if(this.getRec().intersects(enemy.getRec()))
            {
                this.gamePanel.eneList.remove(enemy);
                this.gamePanel.removeList.add(this);
                break;
            }
        }

    }



    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        go();
        this.hitEne();

    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
