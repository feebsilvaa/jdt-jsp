package br.com.feedev.jdtjsp.controller.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.feedev.jdtjsp.config.util.ApplicationConstants;
import br.com.feedev.jdtjsp.controller.service.ProdutoService;
import br.com.feedev.jdtjsp.exception.ProdutoExistenteException;
import br.com.feedev.jdtjsp.model.bean.CategoriaProduto;
import br.com.feedev.jdtjsp.model.bean.Produto;

@WebServlet(urlPatterns = { "/produtos" })
public class ProdutoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3020240496211414881L;

	private static ProdutoService produtoService;

	public ProdutoServlet() {
		produtoService = new ProdutoService();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acaoParam = request.getParameter("acao");

		if (acaoParam == null) {
			try {
				this.listarProdutos(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("produtos", e.getMessage(), null, request, response);
				return;
			} catch (NullPointerException e) {
				e.printStackTrace();
				this.retornaErroForm("produtos.jsp", "Ocorreu um erro genérico.", null, request, response);
				return;
			}
			return;
		}

		String idParam;
		switch (acaoParam) {
		case "listar":
			try {
				this.listarProdutos(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("produtos", e.getMessage(), null, request, response);
				return;
			} catch (NullPointerException e) {
				e.printStackTrace();
				this.retornaErroForm("produtos.jsp", "Ocorreu um erro genérico.", null, request, response);
				return;
			}
			break;
		case "excluir":
			idParam = request.getParameter("id");
			try {
				this.excluirProduto(Long.valueOf(idParam));
				this.redirectTo("produtos", response);
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("produtos", e.getMessage(), null, request, response);
				return;
			}
			break;
		case "edicao":
			idParam = request.getParameter("id");
			try {
				Produto produto = produtoService.buscarProdutoPorId(Long.valueOf(idParam));
				request.setAttribute("produto", produto);
				this.dispathTo("edita-produto.jsp", request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("produtos", e.getMessage(), null, request, response);
				return;
			} catch (NullPointerException e) {
				e.printStackTrace();
				this.retornaErroForm("produtos.jsp", "Ocorreu um erro genérico.", null, request, response);
				return;
			}
			break;
		default:
			try {
				this.listarProdutos(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				this.retornaErroForm("produtos", e.getMessage(), null, request, response);
				return;
			} catch (NullPointerException e) {
				e.printStackTrace();
				this.retornaErroForm("produtos.jsp", "Ocorreu um erro genérico.", null, request, response);
				return;
			}
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acaoParam = request.getParameter("acao");

		if (acaoParam == null) {
			doGet(request, response);
			return;
		}

		String idParam;
		String nomeParam;
		String precoParam;
		String quantidadeParam;
		String categoriaParam;

		HashMap<String, String> params = new HashMap<String, String>();

		switch (acaoParam) {
		case "salvar":

			nomeParam = request.getParameter("nome");
			precoParam = request.getParameter("preco");
			quantidadeParam = request.getParameter("quantidade");
			categoriaParam = request.getParameter("categoria");

			params.put("nomeParam", nomeParam);
			params.put("precoParam", precoParam);
			params.put("quantidadeParam", quantidadeParam);
			params.put("categoriaParam", categoriaParam);

			if (isFormValido(nomeParam, precoParam, quantidadeParam)) {

				try {
					try {
						this.salvarProduto(new Produto(nomeParam, new BigDecimal(precoParam),
								Integer.valueOf(quantidadeParam), new CategoriaProduto(null, categoriaParam)), request,
								response, params);
					} catch (ProdutoExistenteException e) {
						this.retornaErroForm("produtos?acao=listar", e.getMessage(), params, request, response);
						return;
					} catch (NullPointerException e) {
						e.printStackTrace();
						this.retornaErroForm("produtos?acao=listar", "Ocorreu um erro genérico.", params, request,
								response);
						return;
					}
					request.setAttribute("produtos", produtoService.listar());
					this.redirectTo("produtos", response);
					return;
				} catch (SQLException e) {
					e.printStackTrace();
					this.retornaErroForm("produtos?acao=listar", e.getMessage(), params, request, response);
					return;
				} catch (NullPointerException e) {
					e.printStackTrace();
					this.retornaErroForm("produtos?acao=listar", "Ocorreu um erro genérico.", params, request,
							response);
					return;
				}
			} else {
				this.retornaErroForm("produtos?acao=listar", "Parâmetros obrigatórios.", params, request, response);
				return;
			}

		case "editar":

			idParam = request.getParameter("id");
			nomeParam = request.getParameter("nome");
			precoParam = request.getParameter("preco");
			quantidadeParam = request.getParameter("quantidade");

			params.put("idParam", idParam);
			params.put("nomeParam", nomeParam);
			params.put("precoParam", precoParam);
			params.put("quantidadeParam", quantidadeParam);

			if (isFormValido(nomeParam, precoParam, quantidadeParam)) {
				Produto produtoEditado = new Produto();
				produtoEditado.setNome(nomeParam);
				produtoEditado.setPreco(new BigDecimal(precoParam));
				produtoEditado.setQuantidade(Integer.valueOf(quantidadeParam));
				try {
					try {
						this.editarProduto(Long.valueOf(idParam), produtoEditado, request, response);
					} catch (ProdutoExistenteException e) {
						this.retornaErroForm("produtos?acao=edicao", e.getMessage(), params, request, response);
						return;
					} catch (NullPointerException e) {
						e.printStackTrace();
						this.retornaErroForm("produtos?acao=edicao", "Ocorreu um erro genérico.", params, request,
								response);
						return;
					}
					request.setAttribute("produtos", produtoService.listar());
					this.redirectTo("produtos", response);
					return;
				} catch (SQLException e) {
					e.printStackTrace();
					this.retornaErroForm("produtos?acao=edicao", e.getMessage(), params, request, response);
					return;
				} catch (NullPointerException e) {
					e.printStackTrace();
					this.retornaErroForm("produtos?acao=edicao", "Ocorreu um erro genérico.", params, request,
							response);
					return;
				}
			} else {
				this.retornaErroForm("produtos?acao=edicao", "Parâmetros obrigatórios.", params, request, response);
				return;
			}

		default:
			doGet(request, response);
			break;
		}

	}

	private boolean isFormValido(String nomeParam, String precoParam, String quantidadeParam) {
		return (nomeParam != null && !nomeParam.isEmpty()) && (precoParam != null && !precoParam.isEmpty())
				&& (quantidadeParam != null && !quantidadeParam.isEmpty() && (Double.valueOf(precoParam) >= 0)
						&& (Integer.valueOf(quantidadeParam) >= 0));
	}

	private void excluirProduto(Long id) throws SQLException {
		produtoService.excluir(id);
	}

	private void editarProduto(Long id, Produto produto, HttpServletRequest request, HttpServletResponse response)
			throws ProdutoExistenteException, SQLException {
		// verifica se produto existe
		Produto produtoEncontrado = produtoService.buscarProdutoPorNome(produto.getNome());
		if (produtoEncontrado != null && produtoEncontrado.getId() != id) {
			throw new ProdutoExistenteException();
		} else {
			produtoService.editarProduto(id, produto);
			System.out.println("Editado com sucesso.");
		}
	}

	private void salvarProduto(Produto novoProduto, HttpServletRequest request, HttpServletResponse response,
			HashMap<String, String> params) throws SQLException, ProdutoExistenteException {
		// verifica se produto existe
		if (produtoService.buscarProdutoPorNome(novoProduto.getNome()) == null) {
			produtoService.salvarProduto(novoProduto);
			System.out.println("Cadastrado com sucesso.");
		} else {
			throw new ProdutoExistenteException();
		}
	}

	private void retornaErroForm(String view, String message, HashMap<String, String> params,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(message);
		request.setAttribute(ApplicationConstants.ATTR_ERROR_MESSAGE, message);
		if (params != null) {
			request.setAttribute(ApplicationConstants.ATTR_NOME_FORM, params.get("nomeParam"));
			request.setAttribute(ApplicationConstants.ATTR_PRECO_FORM, params.get("precoParam"));
			request.setAttribute(ApplicationConstants.ATTR_QUANTIDADE_FORM, params.get("quantidadeParam"));
		}
		this.dispathTo(view, request, response);
	}

	private void listarProdutos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Produto> produtos = produtoService.listar();
		List<CategoriaProduto> categoriasProduto = produtoService.listarCategorias();
		request.setAttribute("produtos", produtos);
		request.setAttribute("categoriasProduto", categoriasProduto);
		this.dispathTo("produtos.jsp", request, response);
	}

	private void redirectTo(String view, HttpServletResponse response) throws IOException {
		response.sendRedirect(view);
	}

	private void dispathTo(String view, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(view).forward(request, response);
	}

}
