import org.jetbrains.annotations.NotNull;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends JFrame {

    //窗口
    int width=800;
    int hight=610;
    int count=0;
    int enecount=0;
    Image offscreemImage=null;

    int y=150;

    int state=0;//游戏模式 0未开始 1单人 2双人

    private boolean start = false;

    ArrayList<Bullet>bulletList=new ArrayList<Bullet>();
    ArrayList<Enemy>eneList=new ArrayList<Enemy>();

    ArrayList<Tank> playerList=new ArrayList<Tank>();

    ArrayList<Bullet>removeList=new ArrayList<Bullet>();

    public PlayerOne playerOne = new PlayerOne("images/player1/p1tankU.gif", 125, 510,
            "images/player1/p1tankU.gif","images/player1/p1tankD.gif",
            "images/player1/p1tankL.gif","images/player1/p1tankR.gif", this);
    public PlayerOne playerTwo = new PlayerOne("images/player2/p2tankU.gif", 625, 510,
            "images/player2/p2tankU.gif","images/player2/p2tankD.gif",
            "images/player2/p2tankL.gif","images/player2/p2tankR.gif", this);





    //启动方式
    public void lauch()
    {
        //标题
        setTitle("Tank War");
        //初始大小
        setSize(width,hight);
        //屏幕居中
        setLocationRelativeTo(null);
        //添加关闭事件
        setDefaultCloseOperation(3);
        //用户不能调整大小
        setResizable((false));
        //可见
        setVisible(true);
        //键盘监视器
        this.addKeyListener(new GamePanel.KeyMonitor());

        //重绘
        while(true)
        {
            if(count%100==1&&enecount<999) {
                Random random= new Random();
                int r=random.nextInt(800);
                int m=random.nextInt(600);
                eneList.add(new Enemy("images/enemy1U.gif", r, m, "images/enemy1U.gif", "images/enemy1D.gif", "images/enemy1L.gif", "images/enemy1R.gif", this));
                enecount++;

            }
            repaint();
            try{
                Thread.sleep(25);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    //paint
    public void paint(Graphics c)
    {
        if(offscreemImage==null)
        {
            offscreemImage=this.createImage(width,hight);
        }

        Graphics gImage=offscreemImage.getGraphics();

        //颜色
        gImage.setColor((Color.gray));
        //矩形
        gImage.fillRect(0,0,width,hight);
        //换颜色写题目
        gImage.setColor(Color.blue);
        //改变文字大小
        gImage.setFont(new Font("楷体",Font.BOLD,40));
        //添加文字
        if(state==0)
        {
            gImage.drawString("1.单人闯关", 290, 300);
            gImage.drawString("2.双人对战", 290, 380);
            gImage.drawString("3.游戏帮助", 290, 460);

            gImage.setColor(Color.yellow);
            gImage.setFont(new Font("宋体", Font.BOLD, 20));
            gImage.drawString("适当游戏益脑 过度游戏伤身", 265, 570);
            gImage.drawString("未满18周岁请在家长陪同下游玩", 245, 600);

            gImage.setColor(Color.green);
            gImage.setFont(new Font("Algerian", Font.BOLD, 80));
            gImage.drawString("TankWar", 200, 180);
            gImage.setFont(new Font("宋体", Font.BOLD, 25));
            gImage.drawString("（按键选择模式）", 380, 220);
            playerOne.paintSelf(gImage);
            playerTwo.paintSelf(gImage);
        } else if (state==1) {
            gImage.setColor(Color.WHITE);
            gImage.setFont(new Font("华文彩云", Font.BOLD, 80));
            gImage.drawString("单人无尽模式", 160, 330);

            playerList.add(playerOne);
            for(Tank player:playerList)
            {
                player.paintSelf(gImage);
            }

            for(Bullet bullet:bulletList)
                bullet.paintSelf(gImage);
            bulletList.removeAll(removeList);
            for(Enemy enemy:eneList)
                enemy.paintSelf(gImage);

            count++;
        }else if (state==2) {
            gImage.setColor(Color.green);
            gImage.setFont(new Font("宋体", Font.BOLD, 60));
            gImage.drawString("双人对战即将开始", 145, 310);
            playerOne.paintSelf(gImage);
            playerTwo.paintSelf(gImage);
        }
        else if (state==3) {
            gImage.setColor(Color.green);
            gImage.setFont(new Font("宋体", Font.BOLD, 20));
            gImage.drawString("单人模式使用方wasd控制坦克移动，空格为发射子弹", 105, 250);
            gImage.drawString("第二名玩家通过本地网络连接控制进行对战", 105, 300);
            gImage.drawString("返回主页面请按Q", 105, 350);
            gImage.drawString("游戏开发：neuq202212026-谢京龙", 105, 400);

        }


        //利用缓存解决频闪问题
        c.drawImage(offscreemImage,0,0,null);

    }

    //键盘监视器
    private class KeyMonitor extends KeyAdapter{

        @Override
        public void keyPressed( KeyEvent e)
        {
             int key=e.getKeyCode();
             switch(key){
                 case KeyEvent.VK_1 :
                     state=1;
                     break;
                 case KeyEvent.VK_2 :
                     state=2;
                     break;
                 case KeyEvent.VK_3:
                     state=3;
                     break;
                 case KeyEvent.VK_Q:
                     state=0;
                     break;
                 default:
                     playerOne.keyPressed(e);
                     break;


             }
        }


        public void keyReleased (KeyEvent e)
        {
            playerOne.keyReleased(e);
        }
    }



    //按下键盘

    public static void main(String[] args) {
        GamePanel a=new GamePanel();
         a.lauch();
    }

}

