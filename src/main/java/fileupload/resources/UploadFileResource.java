package fileupload.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import fileupload.UploadFileRepository;
import fileupload.model.FileEO;



@Path("/file")
public class UploadFileResource {
	
	@Autowired
	private UploadFileRepository uploadFileRespository;
	
	private static final String TEMP_FOLDER = System.getProperty("java.io.tmpdir");

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<FileEO> getFiles(){
		return  uploadFileRespository.findAll();
	}
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String uploadedFileLocation = TEMP_FOLDER + File.separator + fileDetail.getFileName();

		// save it
		try {
			File f = new File(uploadedFileLocation);
			writeToFile(uploadedInputStream, uploadedFileLocation);
			FileEO feo = new FileEO();
			feo.setSize(f.length());
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().entity("An internal Exception has occured. Please contact Administrator").build();
		}

		String output = "File uploaded to : " + uploadedFileLocation;
		return Response.status(200).entity(output).build();

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {
		try (OutputStream out = new FileOutputStream(new File(uploadedFileLocation))) {
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		}
	}

}
