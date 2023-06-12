CREATE KEYSPACE journal
    WITH replication = {
        'class': 'SimpleStrategy',
        'replication_factor': 3
        };