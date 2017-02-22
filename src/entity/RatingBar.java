/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Shai Gutman
 */
public class RatingBar extends JPanel implements TableCellRenderer{
    LevelBar lb;
    public RatingBar() {
        super(new GridLayout(1,1));
        ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/imgs/31g.png"));
        ImageProducer ip = defaultIcon.getImage().getSource();
        List<ImageIcon> list = Arrays.asList();
        // 4
        ImageIcon yStar = makeStarImageIcon(ip, 1f, 1f, 0f);
        list = Arrays.asList(yStar, yStar, yStar, yStar, yStar);
        add(makeStarRatingPanel("", new LevelBar(defaultIcon, list, 1)));
        setPreferredSize(new Dimension(80, 16));
        setBorder(null);
    }
    private JPanel makeStarRatingPanel(String title, final LevelBar label) {
        lb = label;
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setBorder(BorderFactory.createTitledBorder(title));
        p.add(label);
        return p;
    }
    private static ImageIcon makeStarImageIcon(ImageProducer ip, float rf, float gf, float bf) {
        return new ImageIcon(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(ip, new SelectedImageFilter(rf, gf, bf))));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellRenderer delegate = table.getCellRenderer(row, column);
        Component c = delegate.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
        return c;
    }
    
    public void setRate(int val) {
        lb.setLevel(val);
    } 
    
    public int getRate() {
        return lb.getLevel();
    } 
}
class LevelBar extends JPanel implements MouseListener, MouseMotionListener {
    private final int gap;
    protected final List<ImageIcon> iconList;
    protected final List<JLabel> labelList = Arrays.asList(
        new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel()
    );
    protected final ImageIcon defaultIcon;
    private int clicked = -1;
    protected LevelBar(ImageIcon defaultIcon, List<ImageIcon> list, int gap) {
        super(new GridLayout(1, 5, gap * 2, gap * 2));
        this.defaultIcon = defaultIcon;
        this.iconList = list;
        this.gap = gap;
        for (JLabel l: labelList) {
            l.setIcon(defaultIcon);
            add(l);
        }
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public void clear() {
        clicked = -1;
        repaintIcon(clicked);
    }
    public int getLevel() {
        return clicked;
    }
    public void setLevel(int l) {
        clicked = l;
        repaintIcon(clicked);
    }
    private int getSelectedIconIndex(Point p) {
        for (int i = 0; i < labelList.size(); i++) {
            Rectangle r = labelList.get(i).getBounds();
            r.grow(gap, gap);
            if (r.contains(p)) {
                return i;
            }
        }
        return -1;
    }
    protected void repaintIcon(int index) {
        for (int i = 0; i < labelList.size(); i++) {
            labelList.get(i).setIcon(i <= index ? iconList.get(i) : defaultIcon);
        }
        repaint();
    }
    @Override public void mouseMoved(MouseEvent e) {
        repaintIcon(getSelectedIconIndex(e.getPoint()));
    }
    @Override public void mouseEntered(MouseEvent e) {
        repaintIcon(getSelectedIconIndex(e.getPoint()));
    }
    @Override public void mouseClicked(MouseEvent e) {
        clicked = getSelectedIconIndex(e.getPoint());
    }
    @Override public void mouseExited(MouseEvent e) {
        repaintIcon(clicked);
    }
    @Override public void mouseDragged(MouseEvent e)  {}
    @Override public void mousePressed(MouseEvent e)  {}
    @Override public void mouseReleased(MouseEvent e) {}
}

class SelectedImageFilter extends RGBImageFilter {
    private final float rf;
    private final float gf;
    private final float bf;
    protected SelectedImageFilter(float rf, float gf, float bf) {
        super();
        this.rf = Math.min(1f, rf);
        this.gf = Math.min(1f, gf);
        this.bf = Math.min(1f, bf);
        canFilterIndexColorModel = false;
    }
//     @Override public int filterRGB(int x, int y, int argb) {
//         Color color = new Color(argb, true);
//         float[] array = new float[4];
//         color.getComponents(array);
//         return new Color(array[0] * filter[0], array[1] * filter[1], array[2] * filter[2], array[3]).getRGB();
//     }
    @Override public int filterRGB(int x, int y, int argb) {
        int r = (int) (((argb >> 16) & 0xFF) * rf);
        int g = (int) (((argb >>  8) & 0xFF) * gf);
        int b = (int) (((argb)       & 0xFF) * bf);
        return (argb & 0xFF000000) | (r << 16) | (g << 8) | (b);
    }
}