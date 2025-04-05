package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

public class UserView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel userView;
    
    public UserView(UserController userController, Integer user_id) {
        setTitle("Lista de compras - Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        setLocationRelativeTo(null);
        
        userView = new JPanel();
        userView.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(userView);
        
        GroupLayout gl_home = new GroupLayout(userView);
        gl_home.setAutoCreateContainerGaps(true);  // Adiciona espaços automáticos entre os componentes
        userView.setLayout(gl_home);

        JLabel title = new JLabel("USUÁRIO");
        title.setFont(new Font("Tahoma", Font.PLAIN, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton editUserButton = new JButton("Editar dados");
        editUserButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        editUserButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UserView.this.dispose(); // Fecha a tela de usuário
        	    new EditUserView(userController, user_id).setVisible(true); // Abre a tela de edição
        	}
        });

        JButton showItemsButton = new JButton("Meus itens");
        showItemsButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        showItemsButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UserView.this.dispose(); // Fecha a tela do usuário
        		ItemController itemController = new ItemController();
        		new ItemListView(itemController, userController, user_id).setVisible(true); // Abre a tela de itens
        	}
        });
        
        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UserView.this.dispose(); // Fecha a tela de login
			    new LoginView().setVisible(true);
            }
        });

        // Centralizando os componentes na tela
        gl_home.setHorizontalGroup(
            gl_home.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)  // Garante que o conteúdo não encoste nas bordas
                .addGroup(gl_home.createParallelGroup(Alignment.CENTER)
                    .addComponent(title)
                    .addComponent(editUserButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showItemsButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(200, Short.MAX_VALUE)  // Garante que o conteúdo não encoste nas bordas
        );

        // Vertical grouping for layout
        gl_home.setVerticalGroup(
            gl_home.createSequentialGroup()
                .addGap(80)  // Espaço no topo
                .addComponent(title)
                .addGap(40)
                .addComponent(editUserButton)
                .addGap(18)
                .addComponent(showItemsButton)
                .addGap(18)
                .addComponent(backButton)
                .addContainerGap(80, Short.MAX_VALUE)  // Espaço na parte inferior
        );
    }
}
