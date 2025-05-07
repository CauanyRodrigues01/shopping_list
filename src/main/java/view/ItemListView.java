package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.ItemController;
import controller.UserController;
import model.ItemModel;

public class ItemListView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel itemListView;
	private DefaultTableModel tableModel;
	private JTable itemTable;

	public ItemListView(ItemController itemController, UserController userController, Integer userId) {
		setTitle("Lista de compras - Itens");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		setLocationRelativeTo(null);

		itemListView = new JPanel();
		itemListView.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(itemListView);
		itemListView.setLayout(new BoxLayout(itemListView, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("MEUS ITENS");
		title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		itemListView.add(title);

		JButton addItemButton = new JButton("Adicionar item");
		addItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addItemButton.addActionListener(e -> {
			ItemListView.this.dispose();
			new AddItemView(itemController, userController, userId).setVisible(true);
		});
		itemListView.add(Box.createVerticalStrut(10));
		itemListView.add(addItemButton);

		JButton backButton = new JButton("Voltar");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.addActionListener(e -> {
			ItemListView.this.dispose();
			new UserView(userController, userId).setVisible(true);
		});
		itemListView.add(Box.createVerticalStrut(10));
		itemListView.add(backButton);

		itemListView.add(Box.createVerticalStrut(20));

		String[] columnNames = { "ID", "Nome", "Quantidade", "Status", "Editar", "Deletar" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int row, int column) {
				return column == 4 || column == 5;
			}
		};

		ArrayList<ItemModel> userItems = itemController.listAllItemsOfUserController(userId);
		if (userItems != null && !userItems.isEmpty()) {
			for (ItemModel item : userItems) {
				Object[] rowData = { item.getId(), item.getName(), item.getQuantity(), item.getIsCompleted(), "Editar",
						"Deletar" };
				tableModel.addRow(rowData);
			}
		}
		
		if (userItems == null || userItems.isEmpty()) {
			ItemListView.this.setVisible(true);
		    JOptionPane.showMessageDialog(this, "Nenhum item encontrado.");
		}

		itemTable = new JTable(tableModel);
		itemTable.setRowHeight(30);

		itemTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer("Editar"));
		itemTable.getColumn("Editar").setCellEditor(new ButtonEditor(new JCheckBox(), "Editar", e -> {
			int row = Integer.parseInt(e.getActionCommand());
			int itemId = (int) itemTable.getValueAt(row, 0);
			new EditItemView(userController, userId, itemId).setVisible(true);
			ItemListView.this.dispose();
		}));
		
		itemTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer("Deletar"));
		itemTable.getColumn("Deletar").setCellEditor(new ButtonEditor(new JCheckBox(), "Deletar", e -> {
			int row = Integer.parseInt(e.getActionCommand());

			// Deferir ação para depois do stopCellEditing
			javax.swing.SwingUtilities.invokeLater(() -> {
				int itemId = (int) itemTable.getValueAt(row, 0);
				String itemNome = (String) itemTable.getValueAt(row, 1);

				int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente remover o item \"" + itemNome + "\"?",
						"Confirmação de Remoção", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					boolean removed = itemController.removeItemController(itemId, userId);
					if (removed) {
						JOptionPane.showMessageDialog(null, "Item \"" + itemNome + "\" removido com sucesso!");
						updateTable(itemController, userId);
					} else {
						JOptionPane.showMessageDialog(null,
								"Não foi possível remover o item. Verifique se ele ainda existe ou pertence a você.");
					}
				}
			});
		}));


		JScrollPane scrollPane = new JScrollPane(itemTable);
		itemListView.add(scrollPane);
	}

	// Renderer de botão para exibição na tabela
	class ButtonRenderer extends JButton implements TableCellRenderer {
		private String label;

		public ButtonRenderer(String label) {
			this.label = label;
			setOpaque(true);
			setText(label);
			setFont(new Font("Tahoma", Font.BOLD, 12));
			setForeground(Color.WHITE);
			setFocusPainted(false);
			setBorderPainted(false);

			if ("Deletar".equals(label)) {
				setBackground(new Color(220, 53, 69)); // vermelho Bootstrap
			} else if ("Editar".equals(label)) {
				setBackground(new Color(0, 123, 255)); // azul Bootstrap
			}
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column) {
			return this;
		}
	}

	// Editor de botão para ações em célula
	// Editor de botão para ações em célula
	class ButtonEditor extends DefaultCellEditor {
		private JButton button;
		private boolean clicked;
		private JTable table;
		private ActionListener action;
		private String label;

		public ButtonEditor(JCheckBox checkBox, String label, ActionListener action) {
			super(checkBox);
			this.action = action;
			this.label = label;
			this.button = new JButton(label);
			button.addActionListener(e -> fireEditingStopped());
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			this.table = table;
			this.clicked = true;

			// Reaplica o estilo sempre que a célula entra em edição
			button.setText(label);
			button.setFont(new Font("Tahoma", Font.BOLD, 12));
			button.setForeground(Color.WHITE);

			if ("Deletar".equals(label)) {
				button.setBackground(new java.awt.Color(220, 53, 69)); // vermelho Bootstrap
			} else if ("Editar".equals(label)) {
				button.setBackground(new java.awt.Color(0, 123, 255)); // azul Bootstrap
			}

			button.setFocusPainted(false);
			button.setBorderPainted(false);

			return button;
		}

		@Override
		public Object getCellEditorValue() {
			if (clicked && action != null) {
				int row = table.getSelectedRow();
				action.actionPerformed(new ActionEvent(table, ActionEvent.ACTION_PERFORMED, String.valueOf(row)));
			}
			clicked = false;
			return label;
		}

		@Override
		public boolean stopCellEditing() {
			clicked = false;
			return super.stopCellEditing();
		}
	}


	public void updateTable(ItemController itemController, Integer userId) {
		tableModel.setRowCount(0); // limpa tudo

		ArrayList<ItemModel> userItems = itemController.listAllItemsOfUserController(userId);
		if (userItems != null) {
			for (ItemModel item : userItems) {
				Object[] rowData = { item.getId(), item.getName(), item.getQuantity(), item.getIsCompleted(), "Editar",
						"Deletar" };
				tableModel.addRow(rowData);
			}
		}
	}
}