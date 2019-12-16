package br.com.feedev.jdtjsp.model.bean;

public class File2Upload {

	Long id;

	String fileName;

	String fileType;

	Long fileSize;

	String fileB64;

	Long idUsuario;

	String miniaturaB64;

	public File2Upload() {
	}

	public File2Upload(Long id, String fileName, String fileType, Long fileSize, String fileB64, String miniaturaB64, Long idUsuario) {
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.fileB64 = fileB64;
		this.miniaturaB64 = miniaturaB64;
		this.idUsuario = idUsuario;
	}

	public File2Upload(Long id, String fileName, String fileType, Long fileSize, String miniaturaB64, Long idUsuario) {
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.miniaturaB64 = miniaturaB64;
		this.idUsuario = idUsuario;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileB64() {
		return fileB64;
	}

	public void setFileB64(String fileB64) {
		this.fileB64 = fileB64;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getMiniaturaB64() {
		return miniaturaB64;
	}

	public void setMiniaturaB64(String miniaturaB64) {
		this.miniaturaB64 = miniaturaB64;
	}

	@Override
	public String toString() {
		return "File2Upload [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", fileSize=" + fileSize
				+ ", idUsuario=" + idUsuario + ", miniaturaB64=" + miniaturaB64 + "...]";
	}

}
