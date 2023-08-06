package br.unb.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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
				disciplina = new Disciplina(disciplinaForm.getNome(), disciplinaForm.getLocal(), disciplinaForm.getTurma(),disciplinaForm.getCurso());
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



	

