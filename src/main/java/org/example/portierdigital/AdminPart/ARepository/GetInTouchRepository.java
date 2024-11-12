package org.example.portierdigital.AdminPart.ARepository;

import org.example.portierdigital.General.Model.GetInTouch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GetInTouchRepository extends JpaRepository<GetInTouch, Long> {
}
