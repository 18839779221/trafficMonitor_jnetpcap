package frame;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import bean.IntranetIP;
import statistics.ArpAnalyse;
import statistics.IntranetIPAnalyse;
import utils.Tool;

public class IpMacMappingFrame extends JFrame{
	private ArpAnalyse arpAna;

	private JScrollPane pane;
	private JTable table;
	private DefaultTableModel tableModel;
	private static final String[] header = { "IP", "MAC" };

	private Map<String, String> ipMacMap;

	public IpMacMappingFrame() {
		arpAna = ArpAnalyse.newInstance();
		ipMacMap = arpAna.getIpMacMapping();
		setTitle("已知IP-Mac映射  " + ipMacMap.size());
		initComponents();
		loadAndShowData();
	}

	private void initComponents() {
		tableModel = new DefaultTableModel(new Object[0][0], header);
		table = new JTable(tableModel);
		table.setRowSorter(new TableRowSorter<DefaultTableModel>(tableModel));
		pane = new JScrollPane(table);
		add(pane);

		setSize(600, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void loadAndShowData() {
		for (String ip : ipMacMap.keySet()) {
			tableModel.addRow(new Object[] { ip ,ipMacMap.get(ip)});
		}
	}
}
