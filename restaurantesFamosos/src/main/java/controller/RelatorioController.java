package controller;

import java.net.URL;

import dao.CidadeDAO;
import dao.EstadoDAO;
import dao.PaisDAO;
import dao.RestauranteDAO;
import factory.DAOFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import model.Estado;
import model.Pais;
import model.Restaurante;
import model.Cidade;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioController {

	@FXML
	private ComboBox<Pais> cbPais;

	@FXML
	private ComboBox<Estado> cbEstado;
	
	@FXML
	private ComboBox<Cidade> cbCidade;

	@FXML
	private ComboBox<Restaurante> cbTipo;
	
	@FXML
	private Button btnSair;

	@FXML
	private Button btnVoltar;
	
	@FXML
	private Button btnGeraRelatorio;

	@FXML
	private Button btn5melhores;

	@FXML
	private Button btn10Melhores;
	
	@FXML
	private Button btn15Melhores;
	
	@FXML
	private Button btn20Melhores;

	@FXML
	private Button btnMaior;

	@FXML
	private Button btnMenor;

	@FXML
	private TableView<Restaurante> tbResultado;

	@FXML
	private Button btnTodos;

	private RestauranteDAO restauranteDAO;

	private PaisDAO paisDAO;
	private EstadoDAO estadoDAO;
	private CidadeDAO cidadeDAO;
	
	
	public RelatorioController() {
		paisDAO = DAOFactory.get().paisDAO();
		cidadeDAO = DAOFactory.get().cidadeDAO();
		estadoDAO = DAOFactory.get().estadoDAO();
	}
	
	@FXML
	void Todos(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/RelatorioTodos.jasper");
		try {
			JRDataSource dataSource = 
					new JRBeanCollectionDataSource(restauranteDAO.todos());
			JasperPrint jasperPrint = JasperFillManager
					.fillReport(url.getPath(), null, dataSource);
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void VinteMelhores(ActionEvent event) {
    	URL url = getClass().getResource("/relatorio/Relatorio20Melhores.jasper");
		try {
			JRDataSource dataSource = 
					new JRBeanCollectionDataSource(restauranteDAO.get20Melhores());
			JasperPrint jasperPrint = JasperFillManager
					.fillReport(url.getPath(), null, dataSource);
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void CincoMelhores(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/Relatorio5Melhores.jasper");
		try {
			JRDataSource dataSource = new JRBeanCollectionDataSource(restauranteDAO.get5Melhores());
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null, dataSource);
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void DezMelhores(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/Relatorio10Melhores.jasper");
		try {
			JRDataSource dataSource = new JRBeanCollectionDataSource(restauranteDAO.get10Melhores());
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null, dataSource);
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void QuinzeMelhores(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/Relatorio15Melhores.jasper");
		try {
			JRDataSource dataSource = new JRBeanCollectionDataSource(restauranteDAO.get15Melhores());
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null, dataSource);
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void MaiorPreco(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/RelatorioMaiorPreco.jasper");
		try {
			JRDataSource dataSource = new JRBeanCollectionDataSource(restauranteDAO.getMaiorPreco());
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null, dataSource);
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void MenorPreco(ActionEvent event) {
		URL url = getClass().getResource("/relatorio/RelatorioMenorPreco.jasper");
		try {
			JRDataSource dataSource = new JRBeanCollectionDataSource(restauranteDAO.getMenorPreco());
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null, dataSource);
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public void Cidade() {
		URL url = getClass().getResource("/relatorio/RelatorioEndereco.jasper");
		try {
			JRDataSource dataSource = new JRBeanCollectionDataSource(restauranteDAO.getEndereco());
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null, dataSource);
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public void Tipo() {
		URL url = getClass().getResource("/relatorio/RelatorioTipo.jasper");
		try {
			JRDataSource dataSource = new JRBeanCollectionDataSource(restauranteDAO.getTipo()); 
			JasperPrint jasperPrint = JasperFillManager.fillReport(url.getPath(), null, dataSource);
			JasperViewer.viewReport(jasperPrint);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void GerarRelatorio(ActionEvent event) {
		if (!cbTipo.getSelectionModel().isEmpty()) {
			Tipo();
		} else if (!cbCidade.getSelectionModel().isEmpty()) {
			Cidade();
		}
	}

	@FXML
	void Voltar(ActionEvent event) {

	}
	
	@FXML
	void Sair(ActionEvent event){
		
	}
	
	
	@FXML
	public void initialize() {
		montaComboPais();
		montaComboEstado();
		montaComboCidade();
	}
	
	private void montaComboPais() {
		cbPais.getItems().addAll(paisDAO.todos());

		cbPais.setCellFactory((comboBox) -> {
			return new ListCell<Pais>() {
				@Override
				protected void updateItem(Pais item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getNome());
					}
				}
			};
		});
		cbPais.setConverter(new StringConverter<Pais>() {
			@Override
			public String toString(Pais pais) {
				if (pais == null) {
					return null;
				} else {
					return pais.getCodigo() + " - " + pais.getNome();
				}
			}

			@Override
			public Pais fromString(String personString) {

				return null;
			}
		});
	}
	
	
	
	private void montaComboEstado() {
		cbEstado.getItems().addAll(estadoDAO.todos());

		cbEstado.setCellFactory((comboBox) -> {
			return new ListCell<Estado>() {
				@Override
				protected void updateItem(Estado item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getNome());
					}
				}
			};
		});
		cbEstado.setConverter(new StringConverter<Estado>() {
			@Override
			public String toString(Estado estado) {
				if (estado == null) {
					return null;
				} else {
					return estado.getCodigo() + " - " + estado.getNome();
				}
			}

			@Override
			public Estado fromString(String personString) {

				return null;
			}
		});
	}

	/*-------------------------------------------------------------------------------------------------------------------------------------*/
	private void montaComboCidade() {
		cbCidade.getItems().addAll(cidadeDAO.todos());

		cbCidade.setCellFactory((comboBox) -> {
			return new ListCell<Cidade>() {
				@Override
				protected void updateItem(Cidade item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getNome());
					}
				}
			};
		});
		cbCidade.setConverter(new StringConverter<Cidade>() {
			@Override
			public String toString(Cidade cidade) {
				if (cidade == null) {
					return null;
				} else {
					return cidade.getCodigo() + " - " + cidade.getNome();
				}
			}

			@Override
			public Cidade fromString(String personString) {

				return null;
			}
		});
	}


}
