import React, { useEffect, useState } from 'react';
import { Layout } from '@consta/header/Layout';

export default function Lot() {
    const [lots, setLots] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [searchCode, setSearchCode] = useState('');

    const [editedLot, setEditedLot] = useState(null);
    const [newLot, setNewLot] = useState(null);

    const filtered = lots.filter(l =>
        l.id.toString().includes(searchCode) ||
        l.lot_name.toLowerCase().includes(searchCode.toLowerCase()) ||
        l.customer_code.toLowerCase().includes(searchCode.toLowerCase()) ||
        l.price.toString().includes(searchCode))



    useEffect(() => {
        fetch('http://localhost:8080/api/lots')
            .then(res => {
                if (!res.ok) throw new Error('Ошибка загрузки');
                return res.json();
            })
            .then(data => {
                setLots(data);
                setLoading(false);
            })
            .catch(err => {
                setError(err.message);
                setLoading(false);
            });
    }, []);

    const handleEdit = (id) => {
        console.log(editedLot)
        const selected = lots.find(l => l.id === id);
        setEditedLot({ ...selected });
        setNewLot(null);
    };

    const handleEditInputChange = (e) => {
        const { name, value } = e.target;
        setEditedLot(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSaveEdit = () => {
        fetch(`http://localhost:8080/api/lots/${editedLot.id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(editedLot),
        })
            .then(res => {
                if (!res.ok) throw new Error('Ошибка при сохранении');
                return res.json();
            })
            .then(data => {
                alert('Лот сохранён!');
                setLots(prev =>
                    prev.map(l => (l.id === data.id ? data : l))
                );
                setEditedLot(null);
            })
            .catch(err => alert('Ошибка: ' + err.message));
    };

    const handleCreateNew = () => {
        setNewLot({
            id: '',
            lot_name: '',
            customer_code:'',
            price:'',
            currency_code:'',
            nds_rate:'',
            place_delivery:'',
            date_delivery:''
        });
    };
const deleteLot = (id) => {
    fetch(`http://localhost:8080/api/lots/delete/${id}`, {
        method: 'DELETE'
    })
        .then(res => {
            if (!res.ok) throw new Error('Ошибка при удалении');
            return res.text();
        })
        .then(msg => {
            alert(msg);
            setLots(prev => prev.filter(lot => lot.id !== id));
        })
        .catch(err => alert('Ошибка: ' + err.message));

}
    const handleNewInputChange = (e) => {
        const { name, value } = e.target;
        setNewLot(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSaveNew = () => {
        fetch('http://localhost:8080/api/lots', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newLot),
        })
            .then(async (res) => {
                if (!res.ok) throw new Error('Ошибка при создании');

                const text = await res.text(); // получаем текст ответа

                // если ответ не пустой, парсим JSON, иначе возвращаем null
                return text ? JSON.parse(text) : null;
            })
            .then(data => {
                alert('Лот создан!');

                // Если data есть — добавляем новый лот, иначе просто очищаем форму
                if (data) {
                    setLots(prev => [...prev, data]);
                }
                setNewLot(null);
            })
            .catch(err => alert('Ошибка: ' + err.message));
    };


    if (loading) return <div>Загрузка...</div>;
    if (error) return <div>Ошибка: {error}</div>;

    return (
        <div>
            <h1 style={{ textAlign: 'center' }}>Список Лотов</h1>

            <Layout
                style={{ paddingTop: '10%' }}
                rowTop={{
                    left: (

                            <div style={{ marginBottom: "10%", display: 'flex', flexDirection: 'column',  alignItems: 'flex-start' }}>
                                <input
                                    type="text"
                                    placeholder="Search"
                                    value={searchCode}
                                    onChange={(e) => setSearchCode(e.target.value)}
                                    style={{ marginRight: "5%", marginBottom:"5%"}}
                                />
                                <button onClick={() => setSearchCode('')} style={{ marginBottom:"5%"}}>Очистить</button>





                            <ul>

                                {filtered.map(lot => (
                                    <li
                                        key={lot.id}
                                        onClick={() => handleEdit(lot.id)}
                                        style={{ cursor: 'pointer' }}
                                    >

                                        {lot.id} - {lot.lot_name}
                                        <div>
                                            <button onClick={() => deleteLot(lot.id)}>Удалить</button>
                                        </div>
                                    </li>
                                ))}
                            </ul>
                        </div>
                    ),
                    center: (
                        <div>
                            <button onClick={handleCreateNew}>Создать новый лот</button>

                            {newLot && (
                                <div style={{ marginTop: 16, display: 'flex', flexDirection: 'column', gap: 8 }}>
                                    <h3>create</h3>
                                    <label>code: <input name="id" value={newLot.id ?? ''} onChange={handleNewInputChange}  /></label>
                                    <label>name: <input name="lot_name" value={newLot.lot_name ?? ''} onChange={handleNewInputChange} /></label>
                                    <label>customer code: <input name="customer_code" value={newLot.customer_code ?? ''} onChange={handleNewInputChange} /></label>
                                    <label>price: <input name="price" value={newLot.price ?? ''} onChange={handleNewInputChange} /></label>
                                    <label>currency code: <input name="currency_code" placeholder="RUB" value={newLot.currency_code ?? ''} onChange={handleNewInputChange} /></label>
                                    <label>nds rate: <input name="nds_rate" value={newLot.nds_rate ?? ''} onChange={handleNewInputChange} /></label>
                                    <label>place delivery: <input name="place_delivery"  value={newLot.place_delivery ?? ''} onChange={handleNewInputChange} /></label>
                                    <label>date delivery: <input name="date_delivery" value={newLot.date_delivery ?? ''} placeholder="xxx-xx-xxT00:00:00" onChange={handleNewInputChange} /></label>

                                    <button onClick={handleSaveNew}>Сохранить</button>
                                    <button onClick={() => setNewLot(null)}>Назад</button>
                                </div>
                            )}
                        </div>
                    ),
                    right: editedLot && (
                        <div style={{ marginTop: 16, display: 'flex', flexDirection: 'column', gap: 8 }}>
                            <h3>Создание нового лота</h3>

                            <label>code: <input name="id" value={editedLot.id ?? ''} onChange={handleEditInputChange} disabled /></label>
                            <label>name: <input name="lot_name" value={editedLot.lot_name ?? ''} onChange={handleEditInputChange} /></label>
                            <label>customer code: <input name="customer_code" value={editedLot.customer_code ?? ''} onChange={handleEditInputChange} /></label>
                            <label>price: <input name="price" value={editedLot.price ?? ''} onChange={handleEditInputChange} /></label>
                            <label>currency code: <input name="currency_code" placeholder="RUB" value={editedLot.currency_code ?? ''} onChange={handleEditInputChange} /></label>
                            <label>nds rate: <input name="nds_rate" value={editedLot.nds_rate ?? ''} onChange={handleEditInputChange} /></label>
                            <label>place delivery: <input name="place_delivery" value={editedLot.place_delivery ?? ''} onChange={handleEditInputChange} /></label>
                            <label>date delivery: <input name="date_delivery" placeholder="xxx-xx-xxT00:00:00" value={editedLot.date_delivery ?? ''} onChange={handleEditInputChange} /></label>
                            <button onClick={handleSaveEdit}>Сохранить</button>
                            <button onClick={() => setEditedLot(null)}>Назад</button>
                        </div>
                    )
                }}
            />
        </div>
    );
}
