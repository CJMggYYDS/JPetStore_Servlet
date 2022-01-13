package org.petstore.persistence;

import org.petstore.domain.Sequence;

public interface SequenceDAO {

    Sequence getSequence(Sequence sequence);

    void updateSequence(Sequence sequence);
}
