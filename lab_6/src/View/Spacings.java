package View;

import javax.swing.*;
import java.awt.*;

public class Spacings extends Component {
    static public int S = 8;
    static public int M = 16;
    static public int L = 32;
    static public int XL = 64;
    static public int XXL = 128;

    static public Component create(int height) {
        return Box.createRigidArea(new Dimension(0, height));
    }
}
