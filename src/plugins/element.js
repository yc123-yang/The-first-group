import Vue from 'vue'
import {
  Button,
  Form,
  Input,
  FormItem,
  Message,
  Container,
  Header,
  Aside,
  Main,
  Menu,
  MenuItem,
  MenuItemGroup,
  Submenu,
  Breadcrumb,
  BreadcrumbItem,
  Card,
  Row,
  Col,
  Table,
  TableColumn,
  Select,
  Option,
  DatePicker,
  Pagination,
  Dialog,
  Tabs,
  TabPane
} from 'element-ui'

Vue.use(TabPane)
Vue.use(Tabs)
Vue.use(Dialog)
Vue.use(Pagination)
Vue.use(DatePicker)
Vue.use(Option)
Vue.use(Select)
Vue.use(TableColumn)
Vue.use(Table)
Vue.use(Col)
Vue.use(Row)
Vue.use(Card)
Vue.use(BreadcrumbItem)
Vue.use(Breadcrumb)
Vue.use(Submenu)
Vue.use(MenuItemGroup)
Vue.use(MenuItem)
Vue.use(Menu)
Vue.use(Main)
Vue.use(Aside)
Vue.use(Header)
Vue.use(Container)
Vue.use(Button)
Vue.use(Form)
Vue.use(Input)
Vue.use(FormItem)
Vue.prototype.$message = Message
