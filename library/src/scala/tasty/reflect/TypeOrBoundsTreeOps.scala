package scala.tasty
package reflect

trait TypeOrBoundsTreeOps extends Core {

  trait TypeOrBoundsTreeAPI {
    def tpe(implicit ctx: Context): TypeOrBounds
  }
  implicit def TypeOrBoundsTreeDeco(tpt: TypeOrBoundsTree): TypeOrBoundsTreeAPI

  // ----- TypeTrees ------------------------------------------------

  trait TypeTreeAPI {
    /** Position in the source code */
    def pos(implicit ctx: Context): Position

    def tpe(implicit ctx: Context): Type
    def symbol(implicit ctx: Context): Symbol
  }
  implicit def TypeTreeDeco(tpt: TypeTree): TypeTreeAPI

  val IsTypeTree: IsTypeTreeModule
  abstract class IsTypeTreeModule {
    def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[TypeTree]
    def unapply(parent: TermOrTypeTree)(implicit ctx: Context, dummy: DummyImplicit): Option[TypeTree]
  }

  val TypeTree: TypeTreeModule
  abstract class TypeTreeModule extends TypeTreeCoreModule {

    val IsInferred: IsInferredModule
    abstract class IsInferredModule {
      /** Matches any Inferred and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[Inferred]
    }

    trait InferredAPI {
    }
    implicit def InferredDeco(x: Inferred): InferredAPI

    /** TypeTree containing an inferred type */
    val Inferred: InferredModule
    abstract class InferredModule {
      /** Matches a TypeTree containing an inferred type */
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Boolean
    }

    val IsIdent: IsIdentModule
    abstract class IsIdentModule {
      /** Matches any Ident and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[Ident]
    }

    trait IdentAPI {
      def name(implicit ctx: Context): String
    }
    implicit def IdentDeco(x: Ident): IdentAPI

    val Ident: IdentModule
    abstract class IdentModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[String]
    }

    val IsSelect: IsSelectModule
    abstract class IsSelectModule {
      /** Matches any Select and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[Select]
    }

    trait SelectAPI {
      def qualifier(implicit ctx: Context): Term
      def name(implicit ctx: Context): String
    }
    implicit def SelectDeco(x: Select): SelectAPI

    val Select: SelectModule
    abstract class SelectModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(Term, String)]
    }

    val IsProject: IsProjectModule
    abstract class IsProjectModule {
      /** Matches any Project and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[Project]
    }

    trait ProjectAPI {
      def qualifier(implicit ctx: Context): TypeTree
      def name(implicit ctx: Context): String
    }
    implicit def ProjectDeco(x: Project): ProjectAPI

    val Project: ProjectModule
    abstract class ProjectModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(TypeTree, String)]
    }

    val IsSingleton: IsSingletonModule
    abstract class IsSingletonModule {
      /** Matches any Singleton and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[Singleton]
    }

    trait SingletonAPI {
      def ref(implicit ctx: Context): Term
    }
    implicit def SingletonDeco(x: Singleton): SingletonAPI

    val Singleton: SingletonModule
    abstract class SingletonModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[Term]
    }

    val IsRefined: IsRefinedModule
    abstract class IsRefinedModule {
      /** Matches any Refined and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[Refined]
    }

    trait RefinedAPI {
      def tpt(implicit ctx: Context): TypeTree
      def refinements(implicit ctx: Context): List[Definition]
    }
    implicit def RefinedDeco(x: Refined): RefinedAPI

    val Refined: RefinedModule
    abstract class RefinedModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(TypeTree, List[Definition])]
    }

    val IsApplied: IsAppliedModule
    abstract class IsAppliedModule {
      /** Matches any Applied and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[Applied]
    }

    trait AppliedAPI {
      def tpt(implicit ctx: Context): TypeTree
      def args(implicit ctx: Context): List[TypeOrBoundsTree]
    }
    implicit def AppliedDeco(x: Applied): AppliedAPI

    val Applied: AppliedModule
    abstract class AppliedModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(TypeTree, List[TypeOrBoundsTree])]
    }

    val IsAnnotated: IsAnnotatedModule
    abstract class IsAnnotatedModule {
      /** Matches any Annotated and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[Annotated]
    }

    trait AnnotatedAPI {
      def arg(implicit ctx: Context): TypeTree
      def annotation(implicit ctx: Context): Term
    }
    implicit def AnnotatedDeco(x: Annotated): AnnotatedAPI

    val Annotated: AnnotatedModule
    abstract class AnnotatedModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(TypeTree, Term)]
    }

    val IsAnd: IsAndModule
    abstract class IsAndModule {
      /** Matches any And and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[And]
    }

    trait AndAPI {
      def left(implicit ctx: Context): TypeTree
      def right(implicit ctx: Context): TypeTree
    }
    implicit def AndDeco(x: And): OrAPI

    val And: AndModule
    abstract class AndModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(TypeTree, TypeTree)]
    }

    val IsOr: IsOrModule
    abstract class IsOrModule {
      /** Matches any Or and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[Or]
    }

    trait OrAPI {
      def left(implicit ctx: Context): TypeTree
      def right(implicit ctx: Context): TypeTree
    }
    implicit def OrDeco(x: Or): OrAPI

    val Or: OrModule
    abstract class OrModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(TypeTree, TypeTree)]
    }

    val IsMatchType: IsMatchTypeModule
    abstract class IsMatchTypeModule {
      /** Matches any MatchType and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[MatchType]
    }

    trait MatchTypeAPI {
      def bound(implicit ctx: Context): Option[TypeTree]
      def selector(implicit ctx: Context): TypeTree
      def cases(implicit ctx: Context): List[TypeCaseDef]
    }
    implicit def MatchTypeDeco(x: MatchType): MatchTypeAPI

    val MatchType: MatchTypeModule
    abstract class MatchTypeModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(Option[TypeTree], TypeTree, List[TypeCaseDef])]
    }

    val IsByName: IsByNameModule
    abstract class IsByNameModule {
      /** Matches any ByName and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[ByName]
    }

    trait ByNameAPI {
      def result(implicit ctx: Context): TypeTree
    }
    implicit def ByNameDeco(x: ByName): ByNameAPI

    val ByName: ByNameModule
    abstract class ByNameModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[TypeTree]
    }

    val IsLambdaTypeTree: IsLambdaTypeTreeModule
    abstract class IsLambdaTypeTreeModule {
      /** Matches any LambdaTypeTree and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[LambdaTypeTree]
    }

    trait LambdaTypeTreeAPI {
      def tparams(implicit ctx: Context): List[TypeDef]
      def body(implicit ctx: Context): TypeOrBoundsTree
    }
    implicit def LambdaTypeTreeDeco(x: LambdaTypeTree): LambdaTypeTreeAPI

    val LambdaTypeTree: LambdaTypeTreeModule
    abstract class LambdaTypeTreeModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(List[TypeDef], TypeOrBoundsTree)]
    }

    val IsBind: IsBindModule
    abstract class IsBindModule {
      /** Matches any Bind and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[Bind]
    }

    trait BindAPI {
      def name(implicit ctx: Context): String
      def body(implicit ctx: Context): TypeOrBoundsTree
    }
    implicit def BindDeco(x: Bind): BindAPI

    val Bind: BindModule
    abstract class BindModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(String, TypeOrBoundsTree)]
    }

    val IsBlock: IsBlockModule
    abstract class IsBlockModule {
      /** Matches any Block and returns it */
      def unapply(tpt: TypeOrBoundsTree)(implicit ctx: Context): Option[Block]
    }

    trait BlockAPI {
      def aliases(implicit ctx: Context): List[TypeDef]
      def tpt(implicit ctx: Context): TypeTree
    }
    implicit def BlockDeco(x: Block): BlockAPI

    val Block: BlockModule
    abstract class BlockModule {
      def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(List[TypeDef], TypeTree)]
    }
  }

  // ----- TypeBoundsTrees ------------------------------------------------

  trait TypeBoundsTreeAPI {
    def tpe(implicit ctx: Context): TypeBounds
    def low(implicit ctx: Context): TypeTree
    def hi(implicit ctx: Context): TypeTree
  }
  implicit def TypeBoundsTreeDeco(tpt: TypeBoundsTree): TypeBoundsTreeAPI

  val IsTypeBoundsTree: IsTypeBoundsTreeModule
  abstract class IsTypeBoundsTreeModule {
    def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[TypeBoundsTree]
  }

  val TypeBoundsTree: TypeBoundsTreeModule
  abstract class TypeBoundsTreeModule {
    def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Option[(TypeTree, TypeTree)]
  }

  /** TypeBoundsTree containing wildcard type bounds */
  val WildcardTypeTree: WildcardTypeTreeModule
  abstract class WildcardTypeTreeModule {
    /** Matches a TypeBoundsTree containing wildcard type bounds */
    def unapply(typeOrBoundsTree: TypeOrBoundsTree)(implicit ctx: Context): Boolean
  }

  implicit def typeTreeAsParent(term: TypeTree): TermOrTypeTree
}
