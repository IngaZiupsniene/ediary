package demo.service.parentsService;

import demo.model.Parents;
import demo.repository.IParentsRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentsServiceImp implements IParentsService {
    @Autowired
    IParentsRep iParentsRep;

    @Override
    public List<Parents> parentList() {
        return iParentsRep.findAll();
    }

    @Override
    public Parents save(Parents parents) {
        return iParentsRep.save(parents);
    }

    @Override
    public void delete(long id) {
iParentsRep.delete(id);
    }

    @Override
    public Parents findById(long id) {
        return iParentsRep.findOne(id);
    }

    @Override
    public Parents saveandflush(Parents parents) {
        return iParentsRep.saveAndFlush(parents);
    }
}
