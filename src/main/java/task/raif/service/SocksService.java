package task.raif.service;

import org.springframework.stereotype.Service;
import task.raif.enumContainer.Operations;
import task.raif.exception.NotFoundException;
import task.raif.model.Sock;
import task.raif.model.SocksLot;
import task.raif.model.SocksStorage;
import task.raif.repository.SocksMySQLRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SocksService {

    private final SocksMySQLRepository repository;

    public SocksService(SocksMySQLRepository repository) {
        this.repository = repository;
    }

    public List<SocksLot> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                            .map(o -> new SocksLot(o.getColor(), o.getCottonPart(), o.getQuantity()))
                            .collect(Collectors.toList());
    }

    public long get(Sock sock, Operations operator) {
        switch (operator) {
            case MORE_THEN:
                return repository.getQuantityByColorAndCottonPartMoreThen(sock.getColor(), sock.getCottonPart())
                                 .orElse(0);
            case LESS_THEN:
                return repository.getQuantityByColorAndCottonPartLessThen(sock.getColor(), sock.getCottonPart())
                                 .orElse(0);
            case EQUAL:
                var socksStorage = repository
                        .getByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
                return socksStorage.isPresent() ? socksStorage.get().getQuantity()
                                                : 0;

            default:
                throw new NotFoundException();
        }
    }

    public SocksLot put(SocksLot lot) {
        var socksInStorage = repository.getByColorAndCottonPart(lot.getColor(), lot.getCottonPart());
        if (socksInStorage.isPresent()) {
            var socks = socksInStorage.get();
            socks.setQuantity(socks.getQuantity() + lot.getQuantity());
            repository.save(socks);
        } else {
            repository.save(new SocksStorage(lot.getColor(), lot.getCottonPart(), lot.getQuantity()));
        }
        return lot;

    }

    public SocksLot take(SocksLot lot) {
        var socksInStorage = repository.getByColorAndCottonPart(lot.getColor(), lot.getCottonPart());
        if (socksInStorage.isPresent()) {
            var socks = socksInStorage.get();
            if (socks.getQuantity() >= lot.getQuantity()) {
                if (socks.getQuantity() == lot.getQuantity()) {
                    repository.deleteById(socks.getId());
                } else {
                    socks.setQuantity(socks.getQuantity() - lot.getQuantity());
                    repository.save(socks);
                }
                return lot;
            }
        }
        throw new NotFoundException();
    }

}
