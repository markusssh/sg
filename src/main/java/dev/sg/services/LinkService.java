package dev.sg.services;

import dev.sg.entities.LinkEntity;
import dev.sg.entities.ReportEntity;
import dev.sg.repositories.LinkRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepo linkRepo;

    public List<LinkEntity> saveLinks(
            String[] links,
            ReportEntity reportEntity
    ) {
        List<LinkEntity> linkEntityList = new ArrayList<>();
        for (String link :
                links) {
            LinkEntity entity = LinkEntity
                    .builder()
                    .link(link)
                    .report(reportEntity)
                    .build();
            linkEntityList.add(linkRepo.save(entity));
        }
        return linkEntityList;
    }

}
