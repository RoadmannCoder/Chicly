package com.chickly.PresentationLayer.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/upload-image")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
        maxFileSize = 1024 * 1024 * 10,       // 10 MB
        maxRequestSize = 1024 * 1024 * 15     // 15 MB
)
public class ImageUploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "img"; // Folder where the images will be stored

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the uploaded file
        Part filePart = req.getPart("imageFile"); // "imageFile" is the name attribute in the form
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Specify the folder where the image will be stored
        String appPath = req.getServletContext().getRealPath(""); // Root path of your web application
        String uploadPath = appPath + File.separator + UPLOAD_DIR;

        // Create the img directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Save the file to the specified directory
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath); // Save the uploaded file

        // Construct the URL to access the uploaded image
        String imageUrl = req.getContextPath() + "/" + UPLOAD_DIR + "/" + fileName;

        // Send the image URL as the response
        resp.setContentType("text/plain");
        resp.getWriter().write(imageUrl);
    }
}
