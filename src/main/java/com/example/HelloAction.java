package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.unb.dao.PessoaDAO;
import br.unb.dominio.Endereco;
import br.unb.dominio.Pessoa;
import br.unb.dominio.Projeto;

public class HelloAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		HelloForm helloForm = (HelloForm) form;
		PessoaDAO dao = new PessoaDAO();
		// Criando uma nova pessoa
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Eduardo da Silva");
		pessoa.setIdade(30);
		pessoa.setSexo("M");

		Endereco e = new Endereco();
		e.setCidade("Brasilia");
		e.setEstado("DF");
		e.setRua("SQS108");
		
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setNome("jader");
		pessoa1.setIdade(30);
		pessoa1.setSexo("M");
//		List<Pessoa> list = new ArrayList();
//		list.add(pessoa1);
//		list.add(pessoa);
//		
		Projeto proj = new Projeto();
		proj.setNome("SIPAC");
		proj = dao.salvar(proj);
		
		pessoa.setEndereco(e);
		pessoa.setProjeto(proj);
		pessoa1.setEndereco(e);
		pessoa1.setProjeto(proj);
		pessoa = dao.salvar(pessoa);
		pessoa1 = dao.salvar(pessoa1);
		System.out.println("Pessoa Salva -> " + pessoa);
		System.out.println("Pessoa Salva -> " + pessoa1);
		Projeto projetoDB = dao.getProjetoById(1L);
		System.out.println(projetoDB);
//		PessoaFisica pf = new PessoaFisica();
//		pf.setCpf("111.111.111-11");
//		pf.setNome("pessoa física");
//		PessoaJuridica pj = new PessoaJuridica();
//		pj.setCnpj("16.747.513/0001-09");
//		pj.setNome("pessoa Juridica");
//		br.unb.dominio.heranca.dao.PessoaDAO hDao = new br.unb.dominio.heranca.dao.PessoaDAO();
//		pj = (PessoaJuridica) hDao.salvar(pj);
//		pf = (PessoaFisica) hDao.salvar(pf);

//		Categoria c = new Categoria();
//		c.setNome("Auto");
//		
//		Produto p = new Produto();
//		p.setCategoria(c);
//		ProdutoDAO dao = new ProdutoDAO();
//		dao.salvar(p);

		helloForm.setMessage("pessoa: " + pessoa + "!");
		return mapping.findForward("success");
	}
}
