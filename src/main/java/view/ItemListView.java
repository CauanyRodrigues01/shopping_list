package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import controller.ItemController;
import controller.UserController;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ItemListView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel itemListView;
    private JTextArea shoppingArea;
    
    public ItemListView(ItemController itemController, UserController userController, Integer user_id) {
        setTitle("Lista de compras - Itens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        setLocationRelativeTo(null);
        
        itemListView = new JPanel();
        itemListView.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(itemListView);
        
        GroupLayout gl_home = new GroupLayout(itemListView);
        gl_home.setAutoCreateContainerGaps(true);  // Adiciona espaços automáticos entre os componentes
        itemListView.setLayout(gl_home);

        JLabel title = new JLabel("MEUS ITENS");
        title.setFont(new Font("Tahoma", Font.PLAIN, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton addItemButton = new JButton("Adicionar item");
        addItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addItemButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ItemListView.this.dispose(); // Fecha a tela de itens
        	    new AddItemView(itemController, userController, user_id).setVisible(true); // Abre a tela de edição
        	}
        });
        
        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ItemListView.this.dispose(); // Fecha a tela de login
			    new UserView(userController, user_id).setVisible(true);
            }
        });


        // Centralizando os componentes na tela
        gl_home.setHorizontalGroup(
            gl_home.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)  // Garante que o conteúdo não encoste nas bordas
                .addGroup(gl_home.createParallelGroup(Alignment.CENTER)
                    .addComponent(title)
                    .addComponent(addItemButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(200, Short.MAX_VALUE)  // Garante que o conteúdo não encoste nas bordas
        );

        // Vertical grouping for layout
        gl_home.setVerticalGroup(
            gl_home.createSequentialGroup()
                .addGap(80)  // Espaço no topo
                .addComponent(title)
                .addGap(40)
                .addComponent(addItemButton)
                .addGap(40)
                .addComponent(backButton)
                .addContainerGap(80, Short.MAX_VALUE)  // Espaço na parte inferior
        );
    }
}
