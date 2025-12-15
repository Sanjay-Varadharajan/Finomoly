    package com.example.Finomoly.controller;


    import com.example.Finomoly.dto.AnomalyDto;
    import com.example.Finomoly.dto.StatsDto;
    import com.example.Finomoly.dto.UserDto;
    import com.example.Finomoly.service.AdminService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.security.Principal;
    import java.util.List;

    @RestController
    @RequestMapping("/api/admin")
    public class AdminController{

        @Autowired
        AdminService adminService;


        @GetMapping("/stats/viewall")
        public ResponseEntity<StatsDto> viewallstats(){
            return adminService.viewallstats();
        }

        @GetMapping("/anomaly/view")
        public ResponseEntity<List<AnomalyDto>> viewanomaly(){
            return adminService.viewanomaly();
        }


        @PostMapping("/admin/add")
        public ResponseEntity<String> addadmin(@RequestBody UserDto userDto, Principal principal){
            return adminService.addadmin(userDto,principal);
        }


    }
