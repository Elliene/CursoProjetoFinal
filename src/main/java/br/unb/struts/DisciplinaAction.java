package br.unb.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
//import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.unb.dao.DisciplinaDAO;
import br.unb.dominio.Disciplina;

public class DisciplinaAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("DisciplinaAction::execute");
		DisciplinaForm disciplinaForm = (DisciplinaForm) form;
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		ActionMessages errors = new ActionErrors();
		    if (disciplinaForm.getNome() == null || disciplinaForm.getNome().trim().isEmpty()) {
		        errors.add("nome", new ActionMessage("error.nome.required"));
		    }
		    if (disciplinaForm.getLocal() == null || disciplinaForm.getLocal().trim().isEmpty()) {
		        errors.add("endereco", new ActionMessage("error.endereco.required"));
		    }
		    if (disciplinaForm.getTurma() == null || disciplinaForm.getTurma().trim().isEmpty()) {
		        errors.add("telefone", new ActionMessage("error.telefone.required"));
		    }
		    
		    if (disciplinaForm.getCurso() == null || disciplinaForm.getCurso().trim().isEmpty()) {
		        errors.add("idade", new ActionMessage("error.idade.required"));
		    }
		    if (!errors.isEmpty()) {
		        // Se houver erros, adicione-os ao request e retorne a página de entrada (input)
		        saveErrors(request, errors);
		    }
		String method = request.getParameter("method");
		System.out.println("DisciplinaAction::execute::method " + method);
		if (method != null && !method.isEmpty()) {
			switch (method) {
			case "salvar":
				Disciplina disciplina = new Disciplina(disciplinaForm.getNome(), disciplinaForm.getLocal(), disciplinaForm.getTurma(),disciplinaForm.getCurso());
				disciplinaDAO.salvar(disciplina);
				break;
			case "editar":
				int id = Integer.parseInt(request.getParameter("id"));
				disciplina = disciplinaDAO.getById(id);
				disciplinaForm.setId(disciplina.getId());
				disciplinaForm.setNome(disciplina.getNome());
				disciplinaForm.setLocal(disciplina.getLocal());
				disciplinaForm.setTurma(disciplina.getTurma());
				disciplinaForm.setCurso(disciplina.getCurso());
				break;
			case "atualizar":
				disciplina = new Disciplina(disciplinaForm.getId(), disciplinaForm.getNome(), disciplinaForm.getLocal(), disciplinaForm.getTurma(),disciplinaForm.getCurso());
				disciplinaDAO.update(disciplina);
				break;
			case "excluir":
				id = Integer.parseInt(request.getParameter("id"));
				disciplinaDAO.delete(id);
				break;
			}
		}
			List<Disciplina> disciplinas = disciplinaDAO.findAll();
			request.setAttribute("disciplinas", disciplinas);
			return mapping.findForward("success");
		}
	}



	

