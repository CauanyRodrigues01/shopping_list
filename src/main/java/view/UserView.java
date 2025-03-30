package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JFrame {
    private JPanel contentPane;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField nicknameField;
    private JButton editButton, saveButton, addWishlistButton, viewWishlistButton;
    private JTextArea wishlistArea;
    private JScrollPane scrollPane;

    public UserView() {
        setTitle("Lista de compras - User View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Criando os componentes do layout
        JLabel title = new JLabel("VISUALIZAÇÃO DE USUÁRIO");
        title.setFont(new Font("Tahoma", Font.PLAIN, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nameLabel = new JLabel("Nome completo:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel nicknameLabel = new JLabel("Nickname:");
        nicknameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameField.setColumns(10);
        nameField.setEditable(false);

        emailField = new JTextField();
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        emailField.setColumns(10);
        emailField.setEditable(false);

        nicknameField = new JTextField();
        nicknameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nicknameField.setColumns(10);
        nicknameField.setEditable(false);

        editButton = new JButton("Editar Meus Dados");
        editButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

        saveButton = new JButton("Salvar");
        saveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        saveButton.setVisible(false);

        addWishlistButton = new JButton("Adicionar Wishlist");
        addWishlistButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

        viewWishlistButton = new JButton("Ver Wishlists");
        viewWishlistButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

        wishlistArea = new JTextArea(10, 30);
        wishlistArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        wishlistArea.setEditable(false);
        wishlistArea.setVisible(false);

        // Criando o JScrollPane e incluindo o wishlistArea nele
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Removendo rolagem horizontal

        JPanel panel = new JPanel();
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(32)
                    .addGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nicknameLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nicknameField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)
                        .addComponent(editButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(addWishlistButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewWishlistButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(wishlistArea, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)) // Adiciona o wishlistArea
                    .addContainerGap(57, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(27)
                    .addComponent(title)
                    .addGap(45)
                    .addComponent(nameLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(nicknameLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(nicknameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(emailLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(editButton)
                    .addGap(18)
                    .addComponent(saveButton)
                    .addGap(18)
                    .addComponent(addWishlistButton)
                    .addGap(18)
                    .addComponent(viewWishlistButton)
                    .addGap(18)
                    .addComponent(wishlistArea, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE) // Ajusta a exibição do wishlistArea
                    .addContainerGap(77, Short.MAX_VALUE))
        );

        panel.setLayout(gl_panel);

        // Adiciona o painel no JScrollPane
        scrollPane.setViewportView(panel);
        
        // Define o JScrollPane como o painel principal da janela
        contentPane.setLayout(new java.awt.BorderLayout());
        contentPane.add(scrollPane, java.awt.BorderLayout.CENTER);

        // Eventos dos botões
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Habilita os campos para edição
                nameField.setEditable(true);
                nicknameField.setEditable(true);
                emailField.setEditable(true);
                saveButton.setVisible(true);
                editButton.setVisible(false);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Salva as alterações
                String name = nameField.getText().trim();
                String nickname = nicknameField.getText().trim();
                String email = emailField.getText().trim();

                // Aqui você pode chamar um método no controlador para atualizar o usuário no banco
                JOptionPane.showMessageDialog(UserView.this, "Dados atualizados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // Desabilita os campos de edição
                nameField.setEditable(false);
                nicknameField.setEditable(false);
                emailField.setEditable(false);
                saveButton.setVisible(false);
                editButton.setVisible(true);
            }
        });

        viewWishlistButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wishlistArea.setVisible(true);
                wishlistArea.setText("Carregando itens...\n"); // Simula o carregamento

                // Aqui você pode chamar um método para buscar os items do usuário no banco
                wishlistArea.append("• Jogo de tabuleiro\n");
                wishlistArea.append("• Livro de programação\n");
            }
        });
    }

    public static void main(String[] args) {
        // Cria e exibe a tela
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UserView frame = new UserView();
                frame.setVisible(true);  // Garante que a janela será visível
            }
        });
    }
}
