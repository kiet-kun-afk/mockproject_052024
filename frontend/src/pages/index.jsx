import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { routes } from '../routes';
import Header from '../components/Header';
import { Fragment } from 'react';

const Root = () => {
  return (
    <Router>
      <Routes>
        {routes.map((item, index) => {
          const path = item.path;
          const Page = item.page;
          // check element child
          const out = item?.outlet ? item?.outlet.map((itemOut, index) => {
            const PageOut = itemOut.page;
            return <Route path={itemOut.path} key={index} element={<PageOut />} />
          }) : null;
          // check header 
          const Layout = item.isShowHeader ? Header : Fragment
          return <Route key={index} path={path} element={
            <Layout>
              <Page />
            </Layout>
          } >
            {out}
          </Route>
        })}
      </Routes>
    </Router>
  )
}

export default Root;