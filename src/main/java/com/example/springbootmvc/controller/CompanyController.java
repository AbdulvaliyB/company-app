package com.example.springbootmvc.controller;

import com.example.springbootmvc.entity.Company;
import com.example.springbootmvc.repository.CompanyRepository;
import com.example.springbootmvc.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyService companyService;

    @GetMapping
    public String getCompanyPage(Model model) {
        model.addAttribute("list", companyRepository.findAll());
        return "company/company";
    }

    @GetMapping("/add")
    public String addPage() {
        return "company/company-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("company") Company company) {
        companyService.add(company);
        return "redirect:/company";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        companyRepository.deleteById(id);
        return "redirect:/company";
    }

    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable(value = "id") Integer id) {
        Optional<Company> byId = companyRepository.findById(id);
        Company company = byId.get();
        model.addAttribute("company", company);
        return "company/company-edit";
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable(value = "id") Integer id, @ModelAttribute Company company) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        Company company1 = optionalCompany.get();
        company1.setId(company.getId());
        company1.setName(company.getName());
        companyRepository.save(company1);
        return "redirect:/company";
    }


}
