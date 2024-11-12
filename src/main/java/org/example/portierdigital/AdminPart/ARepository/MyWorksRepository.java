package org.example.portierdigital.AdminPart.ARepository;

import org.example.portierdigital.General.Model.Portfolio.MyWorks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyWorksRepository extends JpaRepository<MyWorks, Long> {
}
