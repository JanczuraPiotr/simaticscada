package pjpl.scada.run;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import pjpl.s7.command.Command;
import pjpl.s7.common.ConstProcess;
import pjpl.s7.net.ServerCommandThread;
import pjpl.s7.utils.Properties;
import pjpl.scada.process.Process1;
import pjpl.scada.run.ConfigDefault;


/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class MainFrame extends javax.swing.JFrame{
	/**
	 * Creates new form manFrame
	 */
	public MainFrame() {
		initComponents();

		timeStart = System.currentTimeMillis();
		configInit();

		//------------------------------------------------------------------------------
		// inicjacja zmiennych

		time_interval = Long.parseLong(config.getProperty("time_interval"), 10);
		commandQueue = new LinkedBlockingQueue<>();
		processes = new HashMap<>();
		executor = Executors.newScheduledThreadPool(MANAGEMENT_THREADS);

		// inicjacja zmiennych
		//------------------------------------------------------------------------------

		//------------------------------------------------------------------------------
		// procesy

		process1 = new Process1(ConstProcess.PROCESS1_ID, commandQueue);
		processes.put(process1.getProcessId(), process1 );
//		variables = process1.getVariables();
		// procesy
		//------------------------------------------------------------------------------

		try {

			//------------------------------------------------------------------------------
			// demony

			connectThread = new ServerCommandThread(
							config.getProperty("simatic_server_ip")
							, Integer.parseInt(config.getProperty("simatic_server_port"))
							, commandQueue

			);

			// demony
			//------------------------------------------------------------------------------

			//------------------------------------------------------------------------------
			// uruchamianie

			connectThread.start();

			executor.scheduleAtFixedRate(
							process1
							, ( time_interval * 2 ) - ( System.currentTimeMillis() % time_interval)
							, time_interval
							, TimeUnit.MILLISECONDS
			);

			// uruchamianie
			//------------------------------------------------------------------------------

		} catch (IOException ex) {
			Logger.getLogger(SimaticScada.class.getName()).log(Level.SEVERE, null, ex);
		}



	}
	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

    cbx_I_0 = new javax.swing.JCheckBox();
    cbx_I_2 = new javax.swing.JCheckBox();
    cbx_I_1 = new javax.swing.JCheckBox();
    cbx_I_3 = new javax.swing.JCheckBox();
    cbx_I_4 = new javax.swing.JCheckBox();
    cbx_I_5 = new javax.swing.JCheckBox();
    cbx_I_6 = new javax.swing.JCheckBox();
    cbx_I_7 = new javax.swing.JCheckBox();
    tfZmienna_1 = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    tfZmienna_2 = new javax.swing.JTextField();
    tfZmienna_3 = new javax.swing.JTextField();
    tfZmienna_4 = new javax.swing.JTextField();
    cbx_Q_0_0 = new javax.swing.JCheckBox();
    cbx_Q_0_2 = new javax.swing.JCheckBox();
    cbx_Q_0_1 = new javax.swing.JCheckBox();
    cbx_Q_0_3 = new javax.swing.JCheckBox();
    cbx_Q_0_4 = new javax.swing.JCheckBox();
    cbx_Q_0_5 = new javax.swing.JCheckBox();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jButton4 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setName("scadaFrame"); // NOI18N
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    cbx_I_0.setText("wejście 0");
    cbx_I_0.setEnabled(false);
    cbx_I_0.setName("cbx_I_0"); // NOI18N

    cbx_I_2.setText("wejście 2");
    cbx_I_2.setEnabled(false);
    cbx_I_2.setName("cbx_I_2"); // NOI18N

    cbx_I_1.setText("wejście 1");
    cbx_I_1.setEnabled(false);
    cbx_I_1.setName("cbx_I_1"); // NOI18N
    cbx_I_1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbx_I_1ActionPerformed(evt);
      }
    });

    cbx_I_3.setText("wejście 3");
    cbx_I_3.setEnabled(false);
    cbx_I_3.setName("cbx_I_3"); // NOI18N

    cbx_I_4.setText("wejście 4");
    cbx_I_4.setEnabled(false);
    cbx_I_4.setName("cbx_I_4"); // NOI18N

    cbx_I_5.setText("wejście 5");
    cbx_I_5.setEnabled(false);

    cbx_I_6.setText("wejście 6");
    cbx_I_6.setEnabled(false);

    cbx_I_7.setText("wejście 7");
    cbx_I_7.setEnabled(false);

    tfZmienna_1.setToolTipText("");
    tfZmienna_1.setName("tfByte"); // NOI18N
    tfZmienna_1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        tfZmienna_1ActionPerformed(evt);
      }
    });
    tfZmienna_1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
      public void propertyChange(java.beans.PropertyChangeEvent evt) {
        tfZmienna_1PropertyChange(evt);
      }
    });

    jLabel1.setText("zmienna Byte");

    jLabel2.setText("zmienna    Int");

    jLabel3.setText("zmienna DInt");

    jLabel4.setText("zmienna Byte");

    tfZmienna_2.setAutoscrolls(false);
    tfZmienna_2.setName("tfInt"); // NOI18N
    tfZmienna_2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        tfZmienna_2ActionPerformed(evt);
      }
    });

    tfZmienna_3.setToolTipText("");
    tfZmienna_3.setName("tfDInt"); // NOI18N
    tfZmienna_3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        tfZmienna_3ActionPerformed(evt);
      }
    });

    tfZmienna_4.setToolTipText("");
    tfZmienna_4.setName("tfReal"); // NOI18N
    tfZmienna_4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        tfZmienna_4ActionPerformed(evt);
      }
    });

    cbx_Q_0_0.setText("wyjście 0");
    cbx_Q_0_0.setToolTipText("");
    cbx_Q_0_0.setName("cbx_Q_0_0"); // NOI18N

    org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${Q_0_0}"), cbx_Q_0_0, org.jdesktop.beansbinding.BeanProperty.create("selected"));
    bindingGroup.addBinding(binding);

    cbx_Q_0_0.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbx_Q_0_0ActionPerformed(evt);
      }
    });

    cbx_Q_0_2.setText("wyjście 2");
    cbx_Q_0_2.setToolTipText("");
    cbx_Q_0_2.setName("cbx_Q_0_2"); // NOI18N
    cbx_Q_0_2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbx_Q_0_2ActionPerformed(evt);
      }
    });

    cbx_Q_0_1.setText("wyjście 1");
    cbx_Q_0_1.setToolTipText("");
    cbx_Q_0_1.setName("cbx_Q_0_1"); // NOI18N
    cbx_Q_0_1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbx_Q_0_1ActionPerformed(evt);
      }
    });

    cbx_Q_0_3.setText("wyjście 3");
    cbx_Q_0_3.setToolTipText("");
    cbx_Q_0_3.setName("cbx_Q_0_3"); // NOI18N
    cbx_Q_0_3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbx_Q_0_3ActionPerformed(evt);
      }
    });

    cbx_Q_0_4.setText("wyjście 4");
    cbx_Q_0_4.setToolTipText("");
    cbx_Q_0_4.setName("cbx_Q_0_4"); // NOI18N
    cbx_Q_0_4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbx_Q_0_4ActionPerformed(evt);
      }
    });

    cbx_Q_0_5.setText("wyjście 5");
    cbx_Q_0_5.setToolTipText("");
    cbx_Q_0_5.setName("cbx_Q_0_5"); // NOI18N
    cbx_Q_0_5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbx_Q_0_5ActionPerformed(evt);
      }
    });

    jButton1.setText("ustaw");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jButton2.setText("ustaw");

    jButton3.setText("ustaw");

    jButton4.setText("ustaw");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(22, 22, 22)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(cbx_I_0)
              .addComponent(cbx_I_1)
              .addComponent(cbx_I_2, javax.swing.GroupLayout.Alignment.TRAILING))
            .addComponent(cbx_I_3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbx_I_4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbx_I_5, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(cbx_I_6, javax.swing.GroupLayout.Alignment.TRAILING))
          .addComponent(cbx_I_7))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                  .addGap(1, 1, 1)
                  .addComponent(jLabel2))
                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
              .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(tfZmienna_3)
              .addComponent(tfZmienna_1)
              .addComponent(tfZmienna_2)
              .addComponent(tfZmienna_4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jButton1)
              .addComponent(jButton2)
              .addComponent(jButton3)
              .addComponent(jButton4))
            .addGap(26, 26, 26)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(cbx_Q_0_3, javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(cbx_Q_0_4, javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(cbx_Q_0_1))
                .addComponent(cbx_Q_0_2))
              .addComponent(cbx_Q_0_0, javax.swing.GroupLayout.Alignment.TRAILING)))
          .addComponent(cbx_Q_0_5, javax.swing.GroupLayout.Alignment.TRAILING))
        .addGap(84, 84, 84))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(28, 28, 28)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(tfZmienna_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(cbx_Q_0_0)
              .addComponent(jLabel1)
              .addComponent(jButton1))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(tfZmienna_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jLabel2)
                  .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(tfZmienna_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(tfZmienna_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jLabel4)
                  .addComponent(jButton4)))
              .addGroup(layout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_Q_0_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_Q_0_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_Q_0_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_Q_0_4)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbx_Q_0_5))
          .addGroup(layout.createSequentialGroup()
            .addComponent(cbx_I_0)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbx_I_1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbx_I_2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbx_I_3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbx_I_4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbx_I_5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbx_I_6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cbx_I_7)))
        .addContainerGap(114, Short.MAX_VALUE))
    );

    bindingGroup.bind();

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void cbx_I_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_I_1ActionPerformed
    System.out.println("cbx_I_1ActionPerformed");
  }//GEN-LAST:event_cbx_I_1ActionPerformed

  private void tfZmienna_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfZmienna_2ActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_tfZmienna_2ActionPerformed

  private void tfZmienna_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfZmienna_3ActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_tfZmienna_3ActionPerformed

  private void tfZmienna_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfZmienna_4ActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_tfZmienna_4ActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    System.out.println(tfZmienna_1.getText());
  }//GEN-LAST:event_jButton1ActionPerformed

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
  }//GEN-LAST:event_formWindowClosing

  private void tfZmienna_1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tfZmienna_1PropertyChange
  }//GEN-LAST:event_tfZmienna_1PropertyChange

  private void tfZmienna_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfZmienna_1ActionPerformed

  }//GEN-LAST:event_tfZmienna_1ActionPerformed

  private void cbx_Q_0_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_Q_0_0ActionPerformed
		Q_0_0 = cbx_Q_0_0.isSelected();
		try{
			process1.setQ_0_0(Q_0_0);
		}catch (IOException ex){
			System.err.prinln(ex.getMessage());
		}
  }//GEN-LAST:event_cbx_Q_0_0ActionPerformed

  private void cbx_Q_0_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_Q_0_1ActionPerformed
		Q_0_1 = cbx_Q_0_1.isSelected();
		try{
			process1.setQ_0_1(Q_0_1);
		}catch (IOException ex){
			System.err.prinln(ex.getMessage());
		}
  }//GEN-LAST:event_cbx_Q_0_1ActionPerformed

  private void cbx_Q_0_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_Q_0_2ActionPerformed
		Q_0_2 = cbx_Q_0_2.isSelected();
		try{
			process1.setQ_0_2(Q_0_2);
		}catch (IOException ex){
			System.err.prinln(ex.getMessage());
		}  }//GEN-LAST:event_cbx_Q_0_2ActionPerformed

  private void cbx_Q_0_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_Q_0_3ActionPerformed
		Q_0_3 = cbx_Q_0_3.isSelected();
		try{
			process1.setQ_0_3(Q_0_3);
		}catch (IOException ex){
			System.err.prinln(ex.getMessage());
		}
  }//GEN-LAST:event_cbx_Q_0_3ActionPerformed

  private void cbx_Q_0_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_Q_0_4ActionPerformed
		Q_0_4 = cbx_Q_0_4.isSelected();
		try{
			process1.setQ_0_4(Q_0_4);
		}catch (IOException ex){
			System.err.prinln(ex.getMessage());
		}
  }//GEN-LAST:event_cbx_Q_0_4ActionPerformed

  private void cbx_Q_0_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_Q_0_5ActionPerformed
		Q_0_5 = cbx_Q_0_5.isSelected();
		try{
			process1.setQ_0_5(Q_0_5);
		}catch (IOException ex){
			System.err.prinln(ex.getMessage());
		}
  }//GEN-LAST:event_cbx_Q_0_5ActionPerformed


	public static final String configFile = "SimaticScada.ini";
	public static Properties config;
	public static long timeStart;
	public static long time_interval;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {


		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
//		try {
//			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//				if ("Nimbus".equals(info.getName())) {
//					javax.swing.UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}
//		} catch (ClassNotFoundException ex) {
//			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (InstantiationException ex) {
//			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (IllegalAccessException ex) {
//			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
//			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//		}
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainFrame mf = new MainFrame();
				mf.setLocationRelativeTo(null);
				mf.setVisible(true);
			}
		});
	}



	private static void configInit(){
		try {
			FileReader configReader = new FileReader(configFile);
			config = new pjpl.s7.utils.Properties(new ConfigDefault());
			config.load(configReader);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(SimaticScada.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(SimaticScada.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

//	public  Variables1 variables;
	private static HashMap<Byte, pjpl.s7.process.Process> processes;
	private static ServerCommandThread connectThread;
	private static Process1 process1;
	private static LinkedBlockingQueue<Command> commandQueue;
	private static ScheduledExecutorService executor;

	private String zmienna_1 = "start";
	private String zmienna_2 = "";
	private String zmienna_3 = "";
	private String zmienna_4 = "";

	private boolean Q_0_0 = true;
	private boolean Q_0_1 = true;
	private boolean Q_0_2 = true;
	private boolean Q_0_3 = true;
	private boolean Q_0_4 = true;
	private boolean Q_0_5 = true;
	private boolean I_0_0 = false;
	private boolean I_0_1 = false;
	private boolean I_0_2 = false;
	private boolean I_0_3 = false;
	private boolean I_0_4 = false;
	private boolean I_0_5 = false;
	private boolean I_0_6 = false;
	private boolean I_0_7 = false;

	private static int MANAGEMENT_THREADS = 1;


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox cbx_I_0;
  private javax.swing.JCheckBox cbx_I_1;
  private javax.swing.JCheckBox cbx_I_2;
  private javax.swing.JCheckBox cbx_I_3;
  private javax.swing.JCheckBox cbx_I_4;
  private javax.swing.JCheckBox cbx_I_5;
  private javax.swing.JCheckBox cbx_I_6;
  private javax.swing.JCheckBox cbx_I_7;
  private javax.swing.JCheckBox cbx_Q_0_0;
  private javax.swing.JCheckBox cbx_Q_0_1;
  private javax.swing.JCheckBox cbx_Q_0_2;
  private javax.swing.JCheckBox cbx_Q_0_3;
  private javax.swing.JCheckBox cbx_Q_0_4;
  private javax.swing.JCheckBox cbx_Q_0_5;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JButton jButton4;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JTextField tfZmienna_1;
  private javax.swing.JTextField tfZmienna_2;
  private javax.swing.JTextField tfZmienna_3;
  private javax.swing.JTextField tfZmienna_4;
  private org.jdesktop.beansbinding.BindingGroup bindingGroup;
  // End of variables declaration//GEN-END:variables
}
