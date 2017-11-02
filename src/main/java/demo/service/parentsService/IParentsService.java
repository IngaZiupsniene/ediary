package demo.service.parentsService;

import demo.model.Parents;

import java.util.List;

public interface IParentsService {
    List<Parents> parentList();
    Parents save(Parents parents);
    void delete (long id);
    Parents findById(long id);
    Parents saveandflush(Parents parents);
}
