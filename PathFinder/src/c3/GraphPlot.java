/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author singh
 */
public class GraphPlot extends javax.swing.JFrame implements MouseListener, MouseMotionListener {

    /**
     * Creates new form GraphPlot
     */
    static ArrayList <Vertex> vertices;
    static ArrayList <Edge> edges;
    ArrayList <Edge> path_edge;
    Vertex vert = null;
    Vertex vert1 = null;
    int mode;
    int count = 0;
    int ecount = 0;
    int pathMode = 0;
    int x1 = 0;
    int y1 = 0;
    int slope = 0;
    int x2 = 0;
    int y2 = 0;
    int x = 0;
    int y = 0;
    int loop =0;
    int animationMode = 0;
    String graphicPath = "";
    String [] GP;
    public GraphPlot(ArrayList <Vertex> vertices, ArrayList <Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
//        this.addActionListener(this);
        
        initComponents();
        GraphSheet ob = new GraphSheet();
        ob.setSize(1369,700);
        ob.setLocation(20, 20);
        add(ob);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setEdges(ArrayList <Edge> edges){
        this.edges = edges;
    }    
   

    @Override
    public void mouseMoved(MouseEvent arg0) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void actionPerformed(ActionEvent arg0) {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        dx++;
//        this.repaint();
//        
//    }
//    
    
  
    
    class GraphSheet extends JPanel implements ActionListener {
        Timer T;
        GraphSheet() {
            this.setBackground(Color.CYAN); 
            T = new Timer(10,this);
//            T.start();
//            System.out.println("const " + dx);
        }
        @Override    
        public void paint(Graphics g) {
            super.paint(g);
            for(Vertex v: vertices) {
                g.setColor(Color.yellow);
                g.fillOval(v.getX() - 15, v.getY() - 15, 30, 30);
                g.setColor(Color.BLACK);
                g.drawString(v.getName(), v.getX() - 15 , v.getY() - 15 );
            }
            Graphics2D g2d = (Graphics2D) g;
            Graphics2D game = (Graphics2D) g;
            
            g2d.setColor(Color.magenta);
            for(Edge e: edges) {
                g2d.setStroke(new BasicStroke(3));
                int xx1 = e.getVfrom().getX();
                int yy1 = e.getVfrom().getY();
                int xx2 =  e.getVto().getX();
                int yy2 =  e.getVto().getY();
                g2d.setStroke( new BasicStroke(3));
                g2d.setColor(Color.black);
                g2d.drawString(e.getsWeight(), (xx1 + xx2) / 2 , (yy1 + yy2) / 2 );
                g2d.setColor(Color.magenta);
                g2d.drawLine(e.getVfrom().getX() ,e.getVfrom().getY()  , e.getVto().getX() , e.getVto().getY() );
            }
//            pathMode =1;
            if(pathMode == 1) {
                System.out.println("Inside loop vagana");
                for(Edge e: path_edge) {
                    g2d.setColor(Color.ORANGE);
                    g2d.setStroke(new BasicStroke(6));
                    g2d.drawLine(e.getVfrom().getX() ,e.getVfrom().getY()  , e.getVto().getX() , e.getVto().getY() );                    
                }
                pathMode = 0;
            }
//            System.out.println(dx);
            if(animationMode == 1) {
//                T.start();
//                game.setColor(Color.BLACK);
//                game.drawOval(x - 15, y - 15, 30, 30);
//                game.drawOval(100, 100, 30, 30);
            
                while(loop < GP.length - 1) {
                    for(Edge e: path_edge) {
                        int flago =0;
                        if(e.getFrom().equals(GP[loop]) && e.getTo().equals(GP[loop+1])) {
                            x1 = e.getVfrom().getX();
                            y1 = e.getVfrom().getY();
                            x2 = e.getVto().getX();
                            y2 = e.getVto().getY();
                            flago = 1;
                            T.stop();
                        }

                        if(flago == 1) {
                            x = x1;
                            if(x2 == x1)
                                continue;
                            slope = (y2 - y1) / (x2 - x1);
                            int c = y1*x2 - x1 * y2;
                            y = slope * x + c;
                            x = x - 26;
                            y = y - 50;
                            flago = 0;
                            T.start();
                            game.setColor(Color.BLACK);
                            game.drawOval(x - 15, y - 15, 30, 30);
                            game.drawOval(100, 100, 30, 30);

    //                        animationMode = 1;
    //                        repaint();

                        }                    
                    }
                }
//                animate(game);
            }
//            g2d.drawOval(dx, dx, 30, 30);
        } 
        
//        public void animate(Graphics2D game) {
////            super.paint(g);
//            System.out.println("here");
////            game.setColor(Color.BLACK);
//////            T.start();
////            System.out.println(" x cor " + xexoo);
////            game.drawOval(xexoo - 15, yeyoo - 15 , 30, 30);
////            game.setColor(Color.GREEN);
////            game.drawOval(varX - 15, varY - 15, 30, 30);
////            game.setColor(Color.RED);
////            game.drawOval(horzi - 15, verti - 15, 30, 30);
//
//        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            x++;
            repaint();
//            System.out.println("Action " + dx);
           
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        graphFrom = new javax.swing.JTextField();
        graphTo = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane1.setForeground(new java.awt.Color(255, 51, 51));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 500));
        jScrollPane1.setViewportView(jPanel2);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setForeground(new java.awt.Color(102, 0, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1818, 1533));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1818, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1533, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToggleButton1.setText("Close");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton3);
        jRadioButton3.setText("Vertex");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton4);
        jRadioButton4.setText("Edge");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Mode");

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Path"));

        jLabel2.setText("From");

        jLabel3.setText("To");

        graphFrom.setText("From");

        graphTo.setText("To");

        jButton2.setText("Path");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(graphFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(graphTo)))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(graphFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(graphTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(719, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(263, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        mode = 1; 
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        mode = 2;
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int xx = 0;
        for(Vertex ver: vertices) {
            if( ver.getName().equals(vert.getName()) ) {
                vertices.remove(xx);
                break;
            }      
            xx++;
        }
        int cc =0;
        ArrayList <Edge> nEdges = new ArrayList <Edge> ();
        
        for(Edge edge: edges) {
            if(edge.getFrom().equals(vert.getName()) || edge.getTo().equals(vert.getName()) ) {
                continue;
            }
            System.out.println(edge.toString());
            nEdges.add(edge);
            cc++;
        }
        edges = nEdges;
        System.out.println("new");
        for(Edge edge: edges) {
            System.out.println(edge.toString());
        
        }         
        repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        // TODO add your handling code here:
        System.out.println("Path mode 1 ");
        FormGraph gp = new FormGraph(vertices.size(), vertices);
        
        gp.generate(edges);
        gp.Dijkstra(graphFrom.getText());
        gp.pathEdge(graphTo.getText(),graphFrom.getText());
        gp.graphicPath(graphTo.getText(), graphFrom.getText());
        path_edge = gp.edgePath;
        graphicPath = gp.getGraphicPath();
        graphicPath.trim();
       try {
           if(graphicPath == null) {
               throw new CustomException();
           }
           else {
                graphicPath = graphicPath + " " +graphTo.getText();        
               GP = graphicPath.split(" ");

               pathMode = 1;
               System.out.println(pathMode);
               System.out.println("edge path ");
               for(Edge e: path_edge) {
                   System.out.println(e.toString());
               }
               System.out.println(graphicPath);              
           }
       }
       catch(CustomException e) {
           JOptionPane.showMessageDialog(GraphPlot.this, "Path not found");
       }        

//        animationMode = 1;
//            for(int i =0; i < GP.length - 1; i++) {
//                System.out.println(GP[i]);
//                System.out.println("fuc it");
//                int flago = 0;
//                for(Edge e: path_edge) {
//                    if(e.getFrom().equals(GP[i]) && e.getTo().equals(GP[i+1])) {
//                        x1 = e.getVfrom().getX();
//                        y1 = e.getVfrom().getY();
//                        x2 = e.getVto().getX();
//                        y2 = e.getVto().getY();
//                        flago = 1;
//                    }
//                    
//                    if(flago == 1) {
//                        x = x1;
//                        if(x2 == x1)
//                            continue;
//                        slope = (y2 - y1) / (x2 - x1);
//                        int c = y1*x2 - x1 * y2;
//                        y = slope * x + c;
//                        x = x - 26;
//                        y = y - 50;
//                        flago = 0;
////                        animationMode = 1;
//                        repaint();
//                    }
//                }
//                
//                                                
//                    
//
//                   
//                                 
//            }        
        
        repaint();      
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphPlot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphPlot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphPlot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphPlot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphPlot(vertices, edges).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JTextField graphFrom;
    private javax.swing.JTextField graphTo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
 @Override
    public void mouseDragged(MouseEvent arg0) {
        if(mode ==1) {
            System.out.println("Draggimg");
            if(count == 1) {
                System.out.println("Dragged");
                if(mode == 1) {
                    int xx = 0;
                    System.out.println("Dragged");
                    for(Vertex ver: vertices) {
                        if( ver.getName().equals(vert.getName()) ) {
                            ver.setX(arg0.getX() - 26);
                            ver.setY(arg0.getY() - 50);
                            break;
                        }      
                        xx++;
                    }            
                repaint();
                }
            }
        }
            
    }
    @Override
    public void mouseClicked(MouseEvent arg0) {
        //To change body of generated methods, choose Tools | Templates.
        System.out.println("CLICKED");
        if(mode == 1) {
            int flag = 0;
            int x = arg0.getX();
            int y = arg0.getY();
                x = x - 26;
                y = y - 50;        

            for(Vertex v: vertices) {
                if( Math.abs(x - v.getX() ) <= 20 && Math.abs(y - v.getY()) <= 20 ){
                    flag = 1;
                    vert = v;
                    break;
                }

            }
            if(flag == 0) {
                String name = JOptionPane.showInputDialog("Enter VErtex Name");
                if(name == null) {
                    return;
                }
                Vertex vella = new Vertex(name, x, y);
                vertices.add(vella);            
            }

        }    
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        System.out.println("pressed");
  //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int x = arg0.getX();
        int y = arg0.getY();
        x = x - 26;
        y = y - 50; 
        for(Vertex v: vertices) {
            if( Math.abs(x - v.getX() ) <= 20 && Math.abs(y - v.getY()) <= 20 ){
                vert = v;
                count = 1;
                break;
            }

        }
//        System.out.println("Pressed: " + vert.getName());
             
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        System.out.println("released");
 //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(mode == 1){
            vert = null;
            count = 0;
        }
        if(mode == 2) {
            int xx = arg0.getX() - 26;
            int yy = arg0.getY() - 50;
            for(Vertex v: vertices) {
                if( Math.abs(xx - v.getX() ) <= 20 && Math.abs(yy - v.getY()) <= 20 ){
                    vert1 = v;
                    ecount = 1;
                    break;
                }

            }
            if(ecount ==1 ) {
                String wt = JOptionPane.showInputDialog("Enter Weight");
                double weight = Double.parseDouble(wt);
                Edge e = new Edge(vert, vert1, weight);
                edges.add(e);
                ecount = 0;
                repaint();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
