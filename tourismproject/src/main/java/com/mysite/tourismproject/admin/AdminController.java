/*
 * package com.mysite.tourismproject.admin;
 * 
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping;
 * 
 * 
 * @Controller public class AdminController {
 * 
 * @Autowired private AdminService adminService;
 * 
 * 
 * 
 * 
 * 
 * @GetMapping("/admin/main") public String admin(Model model ) {
 * model.addAttribute("admins", adminService.readlist()); return "admin/main"; }
 * 
 * 
 * 
 * 
 * 
 * 
 * @GetMapping("/admin/readdetail/{cid}") public String detail(Model
 * model,@PathVariable ("cid") Integer cid) {
 * 
 * model.addAttribute("admin", adminService.readdetail(cid));
 * 
 * return "admin/readdetail"; }
 * 
 * 
 * 
 * 
 * 
 * 
 * @PostMapping("admin/update") public String update(@ModelAttribute Admin
 * admin) { adminService.update(admin); return "redirect:/admin/readdetail/" +
 * admin.getCid(); }
 * 
 * 
 * 
 * 
 * }
 */