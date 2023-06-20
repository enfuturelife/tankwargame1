import java.awt.*;
import java.util.ArrayList;
import com.sun.tools.javac.util.List;

import javax.swing.*;

public class EneryBullet extends Bullet{
    public EneryBullet(String img, int x, int y, Direction direction, GamePanel gamePanel) {
        super(img, x, y, direction, gamePanel);
    }

    public  void   hitPlayer()
    {
            ArrayList<Tank> players=this.gamePanel.playerList;
            for(Tank player:players)
            if(this.getRec().intersects(player.getRec()))
            {
                this.gamePanel.playerList.remove(player);
                JOptionPane.showMessageDialog(null,"GameOver!(点击确定后按Q返回)");
                break;

            }


    }




    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        this.go();
        this.hitPlayer();

    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
