import java.awt.*;

public abstract class Tank extends GameObject {
    public int width =40;
    public  int height=50;

    public   int speed =3;
    public Direction direction=Direction.UP;

    //不同方向
    private String upImg; //向上移动时的图片
    private String downImg;//向下移动时的图片
    private String rightImg;//向右移动时的图片
    private String leftImg;//向左移动时的图片



    public Tank(String img, int x, int y, String upImage, String downImage, String leftImage, String rightImage, GamePanel gamePanel) {
        super(img, x, y, gamePanel);
        this.upImg = upImage;
        this.leftImg = leftImage;
        this.downImg = downImage;
        this.rightImg = rightImage;
    }

    public void setImg(String img)
    {
        this.img=Toolkit.getDefaultToolkit().getImage(img);
    }
    public void leftward(){
        direction = Direction.LEFT;
        setImg(leftImg);
        this.x -= speed;

    }
    public void rightward(){
        direction = Direction.RIGHT;
        setImg(rightImg);
        this.x += speed;

    }
    public void upward(){
        direction = Direction.UP;
        setImg(upImg);
        this.y -= speed;

    }
    public void downward(){
        direction = Direction.DOWN;
        setImg(downImg);
        this.y += speed;

    }

    public void attack(){
        Point p=this.getHeadPoint();
        Bullet bullet=new Bullet("images/bulletGreen.gif",p.x,p.y,direction,this.gamePanel);
        this.gamePanel.bulletList.add(bullet);
    }

    public Point getHeadPoint(){
        switch (direction){
            case LEFT :return new Point(x,y+height/2);
            case RIGHT: return new Point(x+width,y+height/2);
            case UP: return new Point(x+width/2,y);
            case DOWN: return new Point(x+width/2,y+height);
            default:return  null;
        }
    }



    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }
    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }


}
