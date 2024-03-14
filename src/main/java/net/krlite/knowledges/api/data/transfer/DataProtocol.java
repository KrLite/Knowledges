package net.krlite.knowledges.api.data.transfer;

import net.krlite.knowledges.api.component.Knowledge;

/**
 * A protocol which refers to a {@link DataInvoker} target.
 * @param <K>   the linked {@link Knowledge} class.
 */
public interface DataProtocol<K extends Knowledge> {
    /**
     * @return the target {@link DataInvoker} holding the linked {@link Knowledge} class {@link K}.
     */
    DataInvoker<K, ?> dataInvoker();
}
