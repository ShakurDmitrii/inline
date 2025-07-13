import React, { useEffect, useState } from 'react';
import { Layout } from '@consta/header/Layout';

export default function Customer() {
    const [customers, setCustomers] = useState([]);
    const [selectedCustomerCode, setSelectedCustomerCode] = useState('');
    const [editedCustomer, setEditedCustomer] = useState(null);
    const [newCustomer, setNewCustomer] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch('http://localhost:8080/api/customers')
            .then((res) => {
                if (!res.ok) throw new Error('Network response was not ok');
                return res.json();
            })
            .then((data) => {
                setCustomers(data);
                setLoading(false);
            })
            .catch((err) => {
                setError(err.message);
                setLoading(false);
            });
    }, []);

    // При выборе контрагента для редактирования
    const handleSelectChange = (e) => {
        const code = e.target.value;
        const selected = customers.find((c) => c.customerCode === code);
        setSelectedCustomerCode(code);
        setEditedCustomer({ ...selected });
    };
    const handleEdit = (customerCode) => {
        const selected = customers.find(c => c.customerCode === customerCode);
        setEditedCustomer({ ...selected });
        setNewCustomer(null);
    };

    // Обработка изменения в форме редактирования
    const handleEditInputChange = (e) => {
        const { name, value, type, checked } = e.target;
        setEditedCustomer((prev) => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value,
        }));
    };

    // Обработка изменения в форме создания нового
    const handleNewInputChange = (e) => {
        const { name, value, type, checked } = e.target;
        setNewCustomer((prev) => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value,
        }));
    };

    // Сохранение изменений редактируемого клиента (PUT)
    const handleSaveEdit = () => {
        fetch(`http://localhost:8080/api/customers/${editedCustomer.customerCode}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(editedCustomer),
        })
            .then((res) => {
                if (!res.ok) throw new Error('Ошибка при сохранении');
                return res.json();
            })
            .then((data) => {
                alert('Данные успешно сохранены!');
                setCustomers((prev) =>
                    prev.map((c) => (c.customerCode === data.customerCode ? data : c))
                );
            })
            .catch((err) => {
                alert('Ошибка: ' + err.message);
            });
    };

    // Создание нового клиента (POST)
    const handleCreateNew = () => {
        setNewCustomer({
            customerCode: '',
            customerName: '',
            customerEmail: '',
            customerInn: '',
            customerKpp: '',
            customerLegalAddress: '',
            customerPostalAddress: '',
            customerCodeMain: '',
            isOrganization: false,
            isPerson: false,
        });
    };

    const handleSaveNew = () => {
        fetch('http://localhost:8080/api/customers', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newCustomer),
        })
            .then((res) => {
                if (!res.ok) throw new Error('Ошибка при создании');
                return res.json();
            })
            .then((data) => {
                alert('Контрагент успешно создан!');
                setCustomers((prev) => [...prev, data]);
                setNewCustomer(null);
            })
            .catch((err) => {
                alert('Ошибка: ' + err.message);
            });
    };

    if (loading) return <div>Загрузка...</div>;
    if (error) return <div>Ошибка: {error}</div>;

    return (
        <div>
            <h1 style={{ textAlign: 'center' }}>List of Customer</h1>

            <Layout
                style={{ paddingTop: '10%' }}
                rowTop={{
                    left: (
                        <div>
                            <ul>
                                {customers.map((customer) => (
                                    <li key={customer.customerCode} onClick={() => handleEdit(customer.customerCode)} style={{cursor: 'pointer'}}>
                                        {customer.customerName} - {customer.customerEmail}
                                    </li>
                                ))}
                            </ul>

                        </div>
                    ),
                    center: (
                        <div>
                            <button onClick={handleCreateNew}>create new</button>

                            {editedCustomer && (
                                <div style={{ marginTop: 16, display: 'flex', flexDirection: 'column', gap: 8 }}>
                                    <h3>Редактирование контрагента</h3>

                                    <label>Code: <input name="customerCode" value={editedCustomer.customerCode ?? ''} onChange={handleEditInputChange} disabled /></label>
                                    <label>Name: <input name="customerName" value={editedCustomer.customerName ?? ''} onChange={handleEditInputChange} /></label>
                                    <label>Email: <input name="customerEmail" value={editedCustomer.customerEmail ?? ''} onChange={handleEditInputChange} /></label>
                                    <label>INN: <input name="customerInn" value={editedCustomer.customerInn ?? ''} onChange={handleEditInputChange} /></label>
                                    <label>KPP: <input name="customerKpp" value={editedCustomer.customerKpp ?? ''} onChange={handleEditInputChange} /></label>
                                    <label>LegalAddress: <input name="customerLegalAddress" value={editedCustomer.customerLegalAddress ?? ''} onChange={handleEditInputChange} /></label>
                                    <label>PostalAddress: <input name="customerPostalAddress" value={editedCustomer.customerPostalAddress ?? ''} onChange={handleEditInputChange} /></label>
                                    <label>CodeMain: <input name="customerCodeMain" value={editedCustomer.customerCodeMain ?? ''} onChange={handleEditInputChange} /></label>
                                    <label>
                                        Organization:
                                        <input
                                            type="checkbox"
                                            name="isOrganization"
                                            checked={editedCustomer.isOrganization || false}
                                            onChange={handleEditInputChange}
                                        />
                                    </label>
                                    <label>
                                        Person:
                                        <input
                                            type="checkbox"
                                            name="isPerson"
                                            checked={editedCustomer.isPerson || false}
                                            onChange={handleEditInputChange}
                                        />
                                    </label>
                                    <button onClick={handleSaveEdit}>Save</button>
                                    <button onClick={() => setEditedCustomer(null)}>Back</button>
                                </div>
                            )}
                        </div>
                    ),
                    right: newCustomer && (

                        <div style={{ marginTop: 16, display: 'flex', flexDirection: 'column', gap: 8 }}>
                            <h3>Создание нового контрагента</h3>
                            <label>Code: <input name="customerCode" value={newCustomer.customerCode ?? ''} onChange={handleNewInputChange} /></label>
                            <label>Name: <input name="customerName" value={newCustomer.customerName ?? ''} onChange={handleNewInputChange} /></label>
                            <label>Email: <input name="customerEmail" value={newCustomer.customerEmail ?? ''} onChange={handleNewInputChange} /></label>
                            <label>INN: <input name="customerInn" value={newCustomer.customerInn ?? ''} onChange={handleNewInputChange} /></label>
                            <label>KPP: <input name="customerKpp" value={newCustomer.customerKpp ?? ''} onChange={handleNewInputChange} /></label>
                            <label>LegalAddress: <input name="customerLegalAddress" value={newCustomer.customerLegalAddress ?? ''} onChange={handleNewInputChange} /></label>
                            <label>PostalAddress: <input name="customerPostalAddress" value={newCustomer.customerPostalAddress ?? ''} onChange={handleNewInputChange} /></label>
                            <label>CodeMain: <input name="customerCodeMain" value={newCustomer.customerCodeMain ?? ''} onChange={handleNewInputChange} /></label>
                            <label>
                                Organization:
                                <input
                                    type="checkbox"
                                    name="isOrganization"
                                    checked={newCustomer.isOrganization || false}
                                    onChange={handleNewInputChange}
                                />
                            </label>
                            <label>
                                Person:
                                <input
                                    type="checkbox"
                                    name="isPerson"
                                    checked={newCustomer.isPerson || false}
                                    onChange={handleNewInputChange}
                                />
                            </label>
                            <button onClick={handleSaveNew}>Save</button>
                            <button onClick={() => setNewCustomer(null)}>Back</button>
                        </div>
                    ),
                }}
            />
        </div>

    );
}