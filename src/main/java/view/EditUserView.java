package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controller.UserController;

public class EditUserView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel editUser;
    private JTextField nameField;
    private JTextField nickNameField;
    private JTextField emailField;

    public EditUserView(UserController userController) {
    	
        setTitle("Lista de compras - edição de usário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        setLocationRelativeTo(null);
        
        editUser = new JPanel();
        editUser.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(editUser);

        JLabel title = new JLabel("EDIÇÃO DE USUÁRIO");
        title.setFont(new Font("Tahoma", Font.PLAIN, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nameLabel = new JLabel("Nome completo:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel nickNameLabel = new JLabel("NickName:");
        nickNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameField.setColumns(10);

        nickNameField = new JTextField();
        nickNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nickNameField.setColumns(10);

        emailField = new JTextField();
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        emailField.setColumns(10);
        
        JButton sendButton = new JButton("ENVIAR");
        sendButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        sendButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String nickName = nickNameField.getText().trim();
                String email = emailField.getText().trim();

                // Limpar os campos após o envio
                nameField.setText("");
                nickNameField.setText("");
                emailField.setText("");
            }
        });      
        
        JButton backButton = new JButton("Voltar");
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	EditUserView.this.dispose(); // Fecha a tela de login
			    new UserView(userController).setVisible(true);
            }
        });


        GroupLayout gl_contentPane = new GroupLayout(editUser);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(32)
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nickNameLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nickNameField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
                        .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(57, Short.MAX_VALUE))
                .addGroup(Alignment.CENTER,
                    gl_contentPane.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.CENTER)
                            .addComponent(title, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
        );

        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGap(27)
                    .addComponent(title)
                    .addGap(45)
                    .addComponent(nameLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(nickNameLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(nickNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(emailLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(55)
                    .addComponent(sendButton)
                    .addGap(18)
                    .addComponent(backButton)
                    .addContainerGap(177, Short.MAX_VALUE))
        );

        editUser.setLayout(gl_contentPane);
    }
}
