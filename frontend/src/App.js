import './App.css';
import { NavbarRail } from '@consta/header/Navbar';
import { DefaultNavbarRailItem } from '@consta/header/Navbar';
import { ImAddressBook, ImBarcode } from 'react-icons/im';
import { Routes, Route, useNavigate } from 'react-router-dom';

import Home from './pages/Home';
import Customer from "./pages/Customer";
import Lot from "./pages/Lot";

function App() {
    const navigate = useNavigate();

    const menu: DefaultNavbarRailItem[] = [
        {
            label: 'Customer',
            icon: () => (
                <ImAddressBook
                    style={{
                        width: '200%',
                        height: '200%',
                        color: 'red',
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                    }}
                />
            ),
            status: 'warning',
            active: true,
        },
        {
            label: 'Lots',
            icon: () => (
                <ImBarcode
                    style={{
                        width: '200%',
                        height: '200%',
                        display: 'flex',
                        color: 'blue',
                        justifyContent: 'center',
                        alignItems: 'center',
                    }}
                />
            ),
        },
    ];

    const onItemClick = (item: DefaultNavbarRailItem) => {
        if (item.label === 'Customer') {
            navigate('/customer');
        } else if (item.label === 'Lots') {
            navigate('/lots'); // добавь маршрут /lots и компонент
        }
    };

    return (
        <div style={{ display: 'flex', height: '100vh', backgroundColor: '#02f8a2' }}>
            <NavbarRail items={menu} onItemClick={onItemClick} style={{ paddingTop: '2%', width: '5%' }} />

            <div style={{ flexGrow: 1, padding: '24px', backgroundColor: '#efefef' }}>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/customer" element={<Customer/>}/>
                    <Route path="/lots" element={<Lot/>}/>

                </Routes>
            </div>
        </div>
    );
}

export default App;
