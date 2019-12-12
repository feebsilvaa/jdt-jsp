package br.com.feedev.jdtjsp.controller.service;

import java.sql.SQLException;

import br.com.feedev.jdtjsp.model.bean.File2Upload;
import br.com.feedev.jdtjsp.model.dao.UsuarioFileUploadsDao;

public class FilesService {
	
	private static UsuarioFileUploadsDao fileDao;

	public FilesService() {
		fileDao = new UsuarioFileUploadsDao();
	}
	
	public File2Upload buscarFilePorId(String idFileParam) throws NumberFormatException, SQLException {
		return fileDao.buscarFilePorId(Long.valueOf(idFileParam));
	}

}
