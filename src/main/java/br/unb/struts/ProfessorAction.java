package br.unb.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.unb.dao.ProfessorDAO;
import br.unb.dominio.Professor;

public class ProfessorAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	System.out.print("ProfessorAcion::execute");
	ProfessorForm professorForm = (ProfessorForm) form;
	ProfessorDAO professorDao = new ProfessorDAO();
    // Realize a validação do professorForm antes de salvar
    ActionMessages errors = new ActionErrors();
    if (professorForm.getNome() == null || professorForm.getNome().trim().isEmpty()) {
        errors.add("nome", new ActionMessage("error.nome.required"));
    }
    if (professorForm.getEndereco() == null || professorForm.getNome().trim().isEmpty()) {
        errors.add("endereco", new ActionMessage("error.endereco.required"));
    }
    if (professorForm.getTelefone() == null || professorForm.getTelefone().trim().isEmpty()) {
        errors.add("telefone", new ActionMessage("error.telefone.required"));
    }
    
    if (professorForm.getIdade() == null || professorForm.getIdade().trim().isEmpty()) {
        errors.add("idade", new ActionMessage("error.idade.required"));
    }
    if (!errors.isEmpty()) {
        // Se houver erros, adicione-os ao request e retorne a página de entrada (input)
        saveErrors(request, errors);
    }
	String method = request.getParameter("method");
	System.out.println("ProfessorAction::execute::method" + method);
	if (method !=null && !method.isEmpty()) {
		switch (method) {
		case "salvar":
			Professor professor = new Professor(professorForm.getNome(), professorForm.getEndereco(), professorForm.getTelefone(), professorForm.getIdade());
			professorDao.salvar(professor);
			break;
		case "editar":
			int id = Integer.parseInt(request.getParameter("id"));
			professor = professorDao.getById(id);
			professorForm.setId(professor.getId());
			professorForm.setNome(professor.getNome());
			professorForm.setEndereco(professor.getEndereco());
			professorForm.setTelefone(professor.getTelefone());
			professorForm.setTelefone(professor.getIdade());
        case "atualizar":
            professor = new Professor(professorForm.getId(), professorForm.getNome(), professorForm.getEndereco(), professorForm.getTelefone(), professorForm.getIdade());
            professorDao.update(professor);
            break;
        case "excluir":
            id = Integer.parseInt(request.getParameter("id"));
            professorDao.delete(id);
            break;
    }
}

    List<Professor> professores = professorDao.findAll();
    request.setAttribute("professores", professores);

    return mapping.findForward("success");

	}


}
