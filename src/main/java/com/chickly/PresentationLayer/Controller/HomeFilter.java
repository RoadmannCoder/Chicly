package com.chickly.PresentationLayer.Controller;
import com.chickly.BussinesLayer.SubProductService;
import com.chickly.DTO.SubProductDTO;
import com.chickly.DTO.SubProductFilterDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/index.jsp","/"})
public class HomeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        SubProductFilterDTO newArrivalsDTO = new SubProductFilterDTO();

        newArrivalsDTO.setIsNewArrival(true);
        newArrivalsDTO.setPageNumber(1);
        newArrivalsDTO.setPageSize(8);
        SubProductService subProductService = new SubProductService();
        List<SubProductDTO> subProductDTOList = subProductService.filterSubProducts(newArrivalsDTO);
        long totalSubProducts = subProductService.countFilteredSubProducts(newArrivalsDTO);
        request.setAttribute("subProducts", subProductDTOList);
        request.setAttribute("totalSubProducts", totalSubProducts);
        request.setAttribute("pageSize", newArrivalsDTO.getPageSize());


        chain.doFilter(request, response);

    }
}
